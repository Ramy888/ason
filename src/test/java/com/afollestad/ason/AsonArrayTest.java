package com.afollestad.ason;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AsonArrayTest {

    private AsonArray<Ason> array;

    @Before public void setup() {
        array = new AsonArray<Ason>()
                .add(new Ason()
                        .put("_id", 1)
                        .put("name", "Aidan")
                        .put("attrs.priority", 2))
                .add(new Ason()
                        .put("_id", 2)
                        .put("name", "Waverly")
                        .put("attrs.priority", 1));
    }

    @Test public void builder_test() {
        String expected = "[{\"name\":\"Aidan\",\"_id\":1,\"attrs\":{\"priority\":2}}," +
                "{\"name\":\"Waverly\",\"_id\":2,\"attrs\":{\"priority\":1}}]";
        assertEquals(array.toString(), expected);
    }

    @Test public void from_string_test() {
        assertEquals(array.size(), 2);

        assertTrue(array.equal(0, "name", "Aidan"));
        assertTrue(array.equal(0, "_id", 1));
        assertTrue(array.equal(0, "attrs.priority", 2));

        assertTrue(array.equal(1, "name", "Waverly"));
        assertTrue(array.equal(1, "_id", 2));
        assertTrue(array.equal(1, "attrs.priority", 1));
    }

    @Test public void remove_test() {
        Ason one = new Ason()
                .put("_id", 1)
                .put("name", "Aidan")
                .put("attrs.priority", 2);
        Ason two = new Ason()
                .put("_id", 2)
                .put("name", "Waverly")
                .put("attrs.priority", 1);
        array = new AsonArray<Ason>()
                .add(one)
                .add(two);
        array.remove(0);
        assertEquals(two, array.get(0));
        assertTrue(array.equal(0, two));
    }

    @Test public void test_pretty_print() {
        array = new AsonArray<Ason>()
                .add(new Ason().put("_id", 1))
                .add(new Ason().put("_id", 2));
        assertEquals("[\n" +
                "    {\"_id\": 1},\n" +
                "    {\"_id\": 2}\n" +
                "]", array.toString(4));
    }

    @Test public void test_string_array() {
        AsonArray<String> array = new AsonArray<String>()
                .add("Hello", "World!");
        assertEquals("[\"Hello\",\"World!\"]", array.toString());

    }

    @Test public void test_primitive_array() {
        AsonArray<Integer> array = new AsonArray<Integer>()
                .add(1, 2, 3, 4);
        assertEquals("[1,2,3,4]", array.toString());
    }
}
