fn main() {
    fh_r = open("http://www.baidu.com","<");
    fh_w = open("file:index.html",">+");
    fh_r from {line: return line; } map {l: return l+"\n"; } forEach {l: write(fh_w,l); };
    close(fh_r);
    close(fh_w);
}
