function heapsort(a:number[]): void {
    var n:number = a.length;

    for (let i:number = Math.floor(n/2-1); i >= 0; i--) {
        heapify(a, n, i);
    }

    for (let i:number = n-1; i >= 0; i--) {
        let tmp:number = a[0];
        a[0] = a[i];
        a[i] = tmp;
        heapify(a, i, 0);
    }
}

function heapify(a:number[], n:number, i:number): void {
    var hi:number = i;
    var l:number = 2*i+1;
    var r:number = 2*i+2;

    if (l < n && a[l] > a[hi]) {
        hi = l;
    }

    if (r < n && a[r] > a[hi]) {
        hi = r;
    }

    if (hi != i) {
        let tmp:number = a[i];
        a[i] = a[hi];
        a[hi] = tmp;
        heapify(a, n, hi);
    }
}

function quicksort(a:number[], lo:number, hi:number): void {
    if (lo < hi) {
        let p:number = partition(a, lo, hi);
        quicksort(a, p+1, hi);
        quicksort(a, lo, p-1);
    }
}

function partition(a:number[], lo:number, hi:number): number {
    var pivot:number = a[hi];
    var i:number = lo - 1;

    for (let j:number = lo; j < hi; j++) {
        if (pivot > a[j]) {
            i++;
            let tmp:number = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    var tmp:number = a[i+1];
    a[i+1] = a[hi];
    a[hi] = tmp;
    return i+1;
}

function main(): void {
    console.log("Hello World");
}

main();
