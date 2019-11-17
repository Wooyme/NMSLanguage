map = import "java.util.HashMap";
pq = import "java.util.PriorityQueue";
fn main() {
    a = map();
    a.put("Hello","你好");
    a.put("Alphabet",["A","B","C"]);
    print(a.get("Hello")[0]+"\n");
    print(a.get("Alphabet")[0]+"\n");
    b = pq();
    b.add(10);
    b.add(5);
    b.add(7);
    print(b.poll()+"\n");
}
