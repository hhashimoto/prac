use assert_cmd::Command;
use predicates::prelude::*;
use std::fs;

// Boxはメモリをヒープに確保する
// dynはError構造体のメソッドに対する呼び出しが動的ディスパッチされることを示す(理由は？？)
type TestResult = Result<(), Box<dyn std::error::Error>>;

#[test]
fn dies_no_args() -> TestResult {
    // ?はOk値のアンパックやErrの伝播に使う（らしい）
    Command::cargo_bin("echor")?
        .assert()
        .failure()
        .stderr(predicate::str::contains("USAGE"));
    // returnキーワードの他、最終行に;なしで記述するとそれが返却される
    Ok(())
}

#[test]
fn hello1() -> TestResult {
    let expected = fs::read_to_string("tests/expected/hello1.txt")?;
    let mut cmd = Command::cargo_bin("echor")?;
    cmd.arg("Hello there").assert().success().stdout(expected);
    Ok(())
}
