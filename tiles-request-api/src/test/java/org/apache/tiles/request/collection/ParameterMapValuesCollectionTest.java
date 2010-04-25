package org.apache.tiles.request.collection;

import static org.easymock.EasyMock.*;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.tiles.request.collection.extractor.EnumeratedValuesExtractor;
import org.apache.tiles.request.collection.extractor.HasKeys;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link ParameterMap#values()}.
 *
 * @version $Rev$ $Date$
 */
public class ParameterMapValuesCollectionTest {
    private HasKeys<String> extractor;

    private ParameterMap map;

    private Collection<String> coll;

    /**
     * Sets up the test.
     */
    @Before
    public void setUp() {
        extractor = createMock(EnumeratedValuesExtractor.class);
        map = new ParameterMap(extractor);
        coll = map.values();
    }

    /**
     * Tests {@link Collection#add(Object)}.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testAdd() {
        coll.add(null);
    }

    /**
     * Tests {@link Collection#addAll(Object)}.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testAddAll() {
        coll.addAll(null);
    }

    /**
     * Tests {@link Collection#clear(Object)}.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testClear() {
        coll.clear();
    }

    /**
     * Tests {@link Collection#contains(Object)}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testContainsValue() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("one");
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("two");

        expect(extractor.getValue("one")).andReturn("value1");
        expect(extractor.getValue("two")).andReturn("value2");

        replay(extractor, keys);
        assertTrue(coll.contains("value2"));
        verify(extractor, keys);
    }

    /**
     * Tests {@link Collection#contains(Object)}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testContainsValueFalse() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("one");
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("two");
        expect(keys.hasMoreElements()).andReturn(false);

        expect(extractor.getValue("one")).andReturn("value1");
        expect(extractor.getValue("two")).andReturn("value2");

        replay(extractor, keys);
        assertFalse(coll.contains("value3"));
        verify(extractor, keys);
    }

    /**
     * Tests {@link Collection#containsAll(Object)}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testContainsAll() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("one");
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("two");

        expect(extractor.getValue("one")).andReturn("value1");
        expect(extractor.getValue("two")).andReturn("value2");

        replay(extractor, keys);
        List<String> coll = new ArrayList<String>();
        coll.add("value1");
        coll.add("value2");
        assertTrue(this.coll.containsAll(coll));
        verify(extractor, keys);
    }

    /**
     * Tests {@link Collection#containsAll(Object)}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testContainsAllFalse() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("one");
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("two");
        expect(keys.hasMoreElements()).andReturn(false);

        expect(extractor.getValue("one")).andReturn("value1");
        expect(extractor.getValue("two")).andReturn("value2");

        replay(extractor, keys);
        List<String> coll = new ArrayList<String>();
        coll.add("value3");
        assertFalse(this.coll.containsAll(coll));
        verify(extractor, keys);
    }

    /**
     * Test method for {@link Collection#isEmpty()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testIsEmpty() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);
        expect(keys.hasMoreElements()).andReturn(true);

        replay(extractor, keys);
        assertFalse(coll.isEmpty());
        verify(extractor, keys);
    }

    /**
     * Test method for {@link Collection#iterator()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testIterator() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("two");

        expect(extractor.getValue("two")).andReturn("value2");

        replay(extractor, keys);
        Iterator<String> entryIt = coll.iterator();
        assertTrue(entryIt.hasNext());
        assertEquals("value2", entryIt.next());
        verify(extractor, keys);
    }

    /**
     * Test method for {@link Collection#iterator()}.
     */
    @SuppressWarnings("unchecked")
    @Test(expected=UnsupportedOperationException.class)
    public void testIteratorRemove() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);

        try {
            replay(extractor, keys);
            coll.iterator().remove();
        } finally {
            verify(extractor, keys);
        }
    }

    /**
     * Tests {@link Collection#remove(Object)}.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testRemove() {
        coll.remove(null);
    }

    /**
     * Tests {@link Collection#removeAll(java.util.Collection)}
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testRemoveAll() {
        coll.removeAll(null);
    }

    /**
     * Tests {@link Collection#retainAll(java.util.Collection)}
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testRetainAll() {
        coll.retainAll(null);
    }

    /**
     * Test method for {@link org.apache.tiles.request.collection.HeaderValuesMap#size()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSize() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("one");
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("two");
        expect(keys.hasMoreElements()).andReturn(false);

        replay(extractor, keys);
        assertEquals(2, coll.size());
        verify(extractor, keys);
    }

    /**
     * Test method for {@link Collection#toArray()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testToArray() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("one");
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("two");
        expect(keys.hasMoreElements()).andReturn(false);

        expect(extractor.getValue("one")).andReturn("value1");
        expect(extractor.getValue("two")).andReturn("value2");

        String[] entryArray = new String[] {"value1", "value2"};

        replay(extractor, keys);
        assertArrayEquals(entryArray, coll.toArray());
        verify(extractor, keys);
    }

    /**
     * Test method for {@link Collection#toArray(Object[])}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testToArrayTArray() {
        Enumeration<String> keys = createMock(Enumeration.class);

        expect(extractor.getKeys()).andReturn(keys);
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("one");
        expect(keys.hasMoreElements()).andReturn(true);
        expect(keys.nextElement()).andReturn("two");
        expect(keys.hasMoreElements()).andReturn(false);

        expect(extractor.getValue("one")).andReturn("value1");
        expect(extractor.getValue("two")).andReturn("value2");

        String[] entryArray = new String[] {"value1", "value2"};

        replay(extractor, keys);
        String[] realArray = new String[2];
        assertArrayEquals(entryArray, coll.toArray(realArray));
        verify(extractor, keys);
    }
}