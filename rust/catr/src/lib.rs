use clap::{App, Arg};
use std::error::Error;
use std::fs::File;
use std::io::{self, BufRead, BufReader};

#[derive(Debug)]
pub struct Config {
    files: Vec<String>,
    number_lines: bool,
    number_nonblank_lines: bool,
}

type MyResult<T> = Result<T, Box<dyn Error>>;

pub fn get_args() -> MyResult<Config> {
    let matches = App::new("catr")
        .version("0.1.0")
        .author("hhashimoto <almite.bridge@gmail.com")
        .about("Rust cat")
        .arg(
            Arg::with_name("number")
                .short("n")
                .long("number")
                .help("Number lines")
                .takes_value(false),
        )
        .arg(
            Arg::with_name("number-nonblank")
                .short("b")
                .long("number-nonblank")
                .help("Number nonblank lines")
                .takes_value(false)
                .conflicts_with("number"),
        )
        .arg(
            Arg::with_name("files")
                .value_name("FILE")
                .help("Input file(s)")
                .default_value("-")
                .min_values(1),
        )
        .get_matches();

    let number_lines = matches.is_present("number");
    let number_nonblank_lines = matches.is_present("number-nonblank");

    Ok(Config {
        files: matches.values_of_lossy("files").unwrap(),
        number_lines: number_lines,
        number_nonblank_lines: number_nonblank_lines,
    })
}

pub fn run(config: Config) -> MyResult<()> {
    for filename in config.files {
        match open(&filename) {
            Err(err) => eprintln!("Failed to open {}: {}", filename, err),
            Ok(buf) => {
                let mut line_number = 1;
                for line in buf.lines().enumerate() {
                    // tupleの分解のためではあるが、これでいいのか……？
                    match line.1 {
                        Err(err) => eprintln!("Failed to read {}: {}", filename, err),
                        Ok(s) => {
                            if config.number_lines {
                                println!("{:6}\t{}", line_number, s);
                                line_number += 1;
                            } else if config.number_nonblank_lines {
                                if s == "" {
                                    println!("");
                                } else {
                                    println!("{:6}\t{}", line_number, s);
                                    line_number += 1;
                                }
                            } else {
                                println!("{}", s);
                            }
                        }
                    }
                }
            }
        }
    }
    Ok(())
}

fn open(filename: &str) -> MyResult<Box<dyn BufRead>> {
    match filename {
        "-" => Ok(Box::new(BufReader::new(io::stdin()))),
        _ => Ok(Box::new(BufReader::new(File::open(filename)?))),
    }
}
