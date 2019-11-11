fn main() {
    shell = open("shell:ls","<");
    shell from { line: println(line); };
}
