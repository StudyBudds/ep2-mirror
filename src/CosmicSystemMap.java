// define class
public class CosmicSystemMap implements BodyIterable, CosmicSystemIndex {

    private Body[] ks = new Body[65];
    private ComplexCosmicSystem[] vs = new ComplexCosmicSystem[65];
    private int count = 0;

    // Creates a hash map from the specified 'system'.
    // The resulting map has multiple (key, value) pairs, one for each
    // body of 'system'. The value is the reference
    // to the system (only the direct parent) to which the body belongs.
    public CosmicSystemMap(ComplexCosmicSystem system) {
        // implement method
        for(Body b : system) {
            put(b, system.getParent(b));
        }
    }

    private int find(Body k) {
        if (k == null) {
            return ks.length - 1;
        }
        int i = k.hashCode() & (ks.length - 2);
        while (ks[i] != null && !ks[i].equals(k)) {
            i = (i + 1) & (ks.length - 2);
        }
        return i;
    }

    public ComplexCosmicSystem put(Body k, ComplexCosmicSystem v) {
        if (k == null) {
            return null;
        }
        int i = find(k);
        ComplexCosmicSystem old = vs[i];
        vs[i] = v;
        if (ks[i] == null) {
            ks[i] = k;
            if (++count >= ks.length * 3 / 4) {
                Body[] oks = ks;
                ComplexCosmicSystem[] ovs = vs;
                ks = new Body[(oks.length << 1) - 1];
                vs = new ComplexCosmicSystem[(oks.length << 1) - 1];
                for (int j = 0; j < oks.length; j++) {
                    if (oks[j] != null) {
                        ks[i = find(oks[j])] = oks[j];
                        vs[i] = ovs[j];
                    }
                }
            }
        }
        return old;
    }

    public ComplexCosmicSystem get(Body k) {
        return vs[find(k)];
    }

    public boolean containsKey(Body k) {
        return ks[find(k)] != null;
    }

    @Override
    public ComplexCosmicSystem getParent(Body b) {
        return vs[find(b)];
    }

    @Override
    public boolean contains(Body b) {
        return b != null ? b.equals(ks[find(b)]) : false;
    }

    @Override
    public BodyIterator iterator() {
        return new MyMapIterator(count, ks);
    }

    public String toString() {
        String s = "";
        if(count == 0) {
            return "";
        }
        for(Body b : this) {
            s = s + b.toString() + ", ";
        }
        return s.substring(0, s.length() - 2);
    }

    public static class MyMapIterator implements BodyIterator {
        public int curr = 0;
        public int capacity;
        public Body[] ks;

        public MyMapIterator(int capacity, Body[] ks) {
            this.capacity = capacity;
            this.ks = ks;
        }

        @Override
        public boolean hasNext() {
            while(curr < ks.length && ks[curr] == null) {
                curr++;
            }
            return curr < ks.length;
        }

        @Override
        public Body next() {
            return ks[curr++];
        }
    }
}