use clap::App;

fn main() {
    // '_'から始まる変数はunusedを明示するもので、コンパイラの警告を抑制できる
    let _matches = App::new("echor")
        .version("0.1.0")
        .author("hhashimoto <almite.bridge@gmail.com>")
        .about("Rust echo")
        .get_matches();
}
