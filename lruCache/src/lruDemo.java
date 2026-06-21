class lruDemo{
    public static void main(String args[]){
        lruService cache = new lruService(3);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        cache.print();
        System.out.println("****************");
        cache.get(1);
        cache.print();
        System.out.println("****************");
        cache.put(4, 40);
        cache.print();
    }
}