import java.util.NoSuchElementException;

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
        if(system != null) {
            for(Body b : system) {
                put(b, system.getParent(b));
            }
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
        /// 1111
        /// 0111
    }

    public boolean halveCapacity() {
        if(ks.length / 2 < count) {
            return false;
        }
        int i;
        Body[] oks = ks;
        ComplexCosmicSystem[] ovs = vs;
        ks = new Body[oks.length >> 1];
        vs = new ComplexCosmicSystem[oks.length >> 1];
        for (int j = 0; j < oks.length; j++) {
            if (oks[j] != null) {
                ks[i = find(oks[j])] = oks[j];
                vs[i] = ovs[j];
            }
        }
        return true;
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

    public boolean equals(Object o) {
        if(o == null || o.getClass() != this.getClass()) return false;
        if(o == this) return true;
        CosmicSystemMap other = (CosmicSystemMap) o;
        if(this.ks.length != other.ks.length || this.count != other.count) return false;

        for(int i = 0; i < this.ks.length; i++) {
            Body key = ks[i];
            ComplexCosmicSystem value = vs[i];
            if(key != null) {
                ComplexCosmicSystem otherValue = other.getParent(key);
                if(!value.equals(otherValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 1: a : A; b : B
    // 2: b : A; a : B
    // (a-A)+(b-B)
    // (b-A)+(a-B)

    public int hashCode() {
        int hash = 0;
        for(int i = 0; i < ks.length; i++) {
            Body key = ks[i];
            ComplexCosmicSystem value = vs[i];
            if(key != null) {
                int keyHash = key.hashCode();
                int valueHash = value.hashCode();
                hash += (keyHash * valueHash) / 23;
            }
        }
        return hash;
    }

    @Override
    public BodyCollection getBodies() {
        return new MyBodyList(this);
    }

    @Override
    public int numberOfBodies() {
        return count;
    }

    @Override
    public BodyIterator iterator() {
        return new KeyIterator(this);
    }

    private void remove(int index) {
        if(index < ks.length) {
            if(this.ks[index] != null) {
                this.count--;
            }
            this.ks[index] = null;
            this.vs[index] = null;
        }
    }

    public static class KeyIterator implements BodyIterator {
        private int curr = 0;
        private final CosmicSystemMap map;
        private final Body[] ks;

        public KeyIterator(CosmicSystemMap map) {
            this.map = map;
            this.ks = map.ks;
        }

        @Override
        public boolean hasNext() {
            while(curr < map.ks.length && map.ks[curr] == null) {
                curr++;
            }

            return curr < map.ks.length;
        }

        @Override
        public Body next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return ks[curr++];
        }

        public void remove() {
            map.remove(curr-1);
        }
    }
}