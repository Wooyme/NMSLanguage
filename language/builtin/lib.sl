fn forEach(this,lambda){
    arr = members(this);
    size = getSize(arr);
    i = 0;
    while(i<size){
        lambda(this[arr[i]],arr[i]);
        i=i+1;
    }
}

fn map(this,lambda){
    result = new();
    arr = members(this);
    size = getSize(arr);
    i = 0;
    while(i<size){
        result[arr[i]] = lambda(this[arr[i]],arr[i]);
        i=i+1;
    }
    return result;
}

fn from(this,lambda){
    line = readln(this);
    i = 0;
    result = new();
    while(!isNull(line)){
        result[i] = lambda(line);
        line = readln(this);
        i = i+1;
    }
    return result;
}