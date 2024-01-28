use clap::{App, Arg};

fn main() {
    // '_'から始まる変数はunusedを明示するもので、コンパイラの警告を抑制できる
    let matches = App::new("echor")
        .version("0.1.0")
        .author("hhashimoto <almite.bridge@gmail.com>")
        .about("Rust echo")
        .arg(
            Arg::with_name("text")
                .value_name("TEXT")
                .help("Input text")
                .required(true)
                .min_values(1),
        )
        .arg(
            Arg::with_name("omit_newline")
                .short("n")
                .help("Do not print newline")
                .takes_value(false),
        )
        .get_matches();

    let text = matches.values_of_lossy("text").unwrap();
    let omit_newline = matches.is_present("omit_newline");
    // if式はelse節が無い場合、ユニット型を返す。そのため、if単独では値を返すことができない。
    let e = if omit_newline { "" }; // ビルドエラー

    print!("{}{}", text.join(" "), e);
}
