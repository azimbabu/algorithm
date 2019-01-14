package misc;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Radius lets customers store records in our database. Records can be arbitrary JSON objects, but customers specify
 * which properties to parse by registering dot-notated attribute names (registering 'address.street' means we would
 * parse "353 Sacramento St" in {"address":{"street":"353 Sacramento St"}}). When customers get records from Radius,
 * they request a set of attributes to return as a JSON object (with nested properties).
 *
 * The goal of this problem is to implement the buildTree method that takes a set of attributes (like address.street,
 * location_name_dba, address.city), a map of attribute name to value (address.street -> '353 Sacramento St',
 * address.city -> 'San Francisco'), and returns an 'unflattened' tree (address -> street: 353 Sac, city: sf) to include
 * and an attribute name to value map and returns the 'unflattened' tree.
 */
public class UnflattenKeys {

    public Map<String, Object> buildTree(Set<String> keys, Map<String, Object> valueMap) {
        if (keys == null || keys.size() != valueMap.size()) {
            throw new RuntimeException("Invalid input");
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        //for (Map.Entry<String, Object> key : valueMap) {   // should be valueMap.entrySet(). poor naming of key
        for (Map.Entry<String, Object> key : valueMap.entrySet()) { // Azim : Corrected
            String[] keyString = key.getKey().split("\\.");
//            resultMap = buildMap(resultMap, keyString, 0, v1);    // v1 is not declared and not initialized. poor naming.
            resultMap = buildMap(resultMap, keyString, 0, valueMap.get(key.getKey()));  // Azim : Corrected
        }

        return resultMap;
    }

//    private HashMap<String, Object> buildMap(HashMap<String, Object> map, String[] keyString, int i, String v1) { // v1 should be of type Object. again poort naming
    private HashMap<String, Object> buildMap(HashMap<String, Object> map, String[] keyString, int i, Object v1) {   // Azim : Corrected
        if (i == keyString.length-1) {
            HashMap<String, Object> m1 = new HashMap<>();
            m1.put(keyString[i], v1);
            return m1;
        }

        // solution
        if (map.containsKey(keyString[i])) {
//            map.put(keyString[i], buildMap(map.get(keyString[i]), keyString, i++, v1));   // wrong. map.get will give Object. Casting is needed
            map.put(keyString[i], buildMap((HashMap<String, Object>) map.get(keyString[i]), keyString, i++, v1));   // Azim : Corrected
        } else {
            HashMap<String, Object> m2 = new HashMap<>();
            map.put(keyString[i], buildMap(m2, keyString, i++, v1));
        }

        // wrong. no return.
        return map; // Azim : Corrected
    }

    public Map<String, Object> buildTree2(Set<String> attributes, Map<String, Object> valueMap) {
        if (attributes == null) {
            return Collections.emptyMap();
        }

        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            String[] keys = entry.getKey().split("\\.");
            populateTree(keys, 0, valueMap.get(entry.getKey()), result);
        }
        return result;
    }

    private void populateTree(String[] keys, int currentIndex, Object value, Map<String, Object> result) {
        if (value == null || isNullList(value)) {
            return;
        }

        if (currentIndex == keys.length-1) {
            result.put(keys[currentIndex], value);
            return;
        }

        String key = keys[currentIndex];
        if (!result.containsKey(key)) {
            result.put(key, new HashMap<>());
        }

        populateTree(keys, currentIndex + 1, value, (Map<String, Object>) result.get(key));
    }

    private boolean isNullList(Object value) {
        if (!(value instanceof Collection)) {
            return false;
        }

        Collection collection = (Collection) value;
        return collection.stream().anyMatch(v -> v == null);
    }

    @Test
    public void basic() {
        Set<String> attributes = Sets.newHashSet("a.b.c", "a.b.d", "b.c");
        Map<String, Object> valueMap = ImmutableMap.of(
                "a.b.c", "v1",
                "a.b.d", "v2",
                "b.c", "v3"
        );

        Map<String, Object> expected = ImmutableMap.of(
                "a", ImmutableMap.of(
                        "b", ImmutableMap.of(
                                "c", "v1",
                                "d", "v2")),
                "b", ImmutableMap.of(
                        "c", "v3"));
        Assert.assertEquals(expected, new UnflattenKeys().buildTree2(attributes, valueMap));
    }

    @Test
    public void nullValue() {
        Set<String> keys = Sets.newHashSet("a.b.c");
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("a.b.c", null);
        Map<String, Object> expected = Collections.emptyMap();
        Assert.assertEquals(expected, new UnflattenKeys().buildTree2(keys, valueMap));
    }

    @Test
    public void nullListValue() {
        Set<String> keys = Sets.newHashSet("a.b.c");
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("a.b.c", Collections.singletonList(null));
        Map<String, Object> expected = Collections.emptyMap();
        Assert.assertEquals(expected, new UnflattenKeys().buildTree2(keys, valueMap));
    }

    @Test
    public void noValue() {
        Set<String> keys = Sets.newHashSet("a.b.c");
        Map<String, Object> valueMap = Collections.emptyMap();
        Map<String, Object> expected = Collections.emptyMap();
        Assert.assertEquals(expected, new UnflattenKeys().buildTree2(keys, valueMap));
    }
}
