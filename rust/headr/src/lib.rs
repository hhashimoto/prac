use clap::{App, Arg};
use std::{
    borrow::Borrow,
    error::Error,
    fs::File,
    io::{self, BufRead, BufReader},
};

type MyResult<T> = Result<T, Box<dyn Error>>;

#[derive(Debug)]
pub struct Config {
    files: Vec<String>,
    lines: usize,
    bytes: Option<usize>,
}

pub fn get_args() -> MyResult<Config> {
    let matches = App::new("headr")
        .version("0.1.0")
        .author("hhashimoto <almite.bridge@gmail.com")
        .about("Rust head")
        .arg(
            Arg::with_name("lines")
                .value_name("LINES")
                .short("n")
                .long("lines")
                .default_value("10")
                .help("print the first NUM lines instead of the first 10"),
        )
        .arg(
            Arg::with_name("bytes")
                .value_name("BYTES")
                .short("c")
                .long("bytes")
                .hide_default_value(true)
                .conflicts_with("lines")
                .help("print the first NUM bytes of each file"),
        )
        .arg(
            Arg::with_name("files")
                .value_name("FILE")
                .min_values(1)
                .default_value("-"),
        )
        .get_matches();

    let lines = matches
        .value_of("lines")
        .map(parse_positive_int)
        .transpose()
        .map_err(|e| format!("illegal line count -- {}", e))?;
    let bytes = matches
        .value_of("bytes")
        .map(parse_positive_int)
        .transpose()
        .map_err(|e| format!("illegal byte count -- {}", e))?;

    Ok(Config {
        files: matches.values_of_lossy("files").unwrap(),
        lines: lines.unwrap(),
        bytes,
    })
}

pub fn run(config: Config) -> MyResult<()> {
    for filename in config.files {
        match open(&filename) {
            Err(err) => eprintln!("{}: {}", filename, err),
            Ok(f) => match config.bytes {
                None => head_lines(config.lines, f),
                _ => head_bytes(config.bytes.unwrap(), f),
            },
        }
    }
    Ok(())
}

fn head_lines(n: usize, f: Box<dyn BufRead>) {
    let mut n2 = n;
    for row in f.lines() {
        if n2 <= 0 {
            return;
        }
        println!("{}", row.unwrap());
        n2 -= 1;
    }
}

fn head_bytes(n: usize, mut f: Box<dyn BufRead>) {
    let mut buf = vec![0; n];
    let b = f.read(&mut buf[..]);
    let s = String::from_utf8_lossy(&buf[..b.unwrap()]);
    print!("{}", s);
}

fn open(filename: &str) -> MyResult<Box<dyn BufRead>> {
    match filename {
        "-" => Ok(Box::new(BufReader::new(io::stdin()))),
        _ => Ok(Box::new(BufReader::new(File::open(filename)?))),
    }
}

fn parse_positive_int(val: &str) -> MyResult<usize> {
    match val.parse() {
        Ok(n) if n > 0 => Ok(n),
        _ => Err(From::from(val)),
    }
}

#[test]
fn test_parse_positive_int() {
    // 3は整数のため、OK
    let res = parse_positive_int("3");
    assert!(res.is_ok());
    assert_eq!(res.unwrap(), 3);

    // 数値以外の文字列はエラー
    let res = parse_positive_int("foo");
    assert!(res.is_err());
    assert_eq!(res.unwrap_err().to_string(), "foo".to_string());

    // 0の場合はpositiveでないためエラー
    let res = parse_positive_int("0");
    assert!(res.is_err());
    assert_eq!(res.unwrap_err().to_string(), "0".to_string());
}
