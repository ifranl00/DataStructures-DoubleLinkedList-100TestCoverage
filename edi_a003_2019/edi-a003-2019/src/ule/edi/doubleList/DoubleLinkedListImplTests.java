package ule.edi.doubleList;



import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;
import static org.junit.Assert.*;

public class DoubleLinkedListImplTests {

	private DoubleLinkedListImpl<String> lS;
	private DoubleLinkedListImpl<String> lSABC;
	private DoubleLinkedListImpl<String> lSABCDE;


	@Before
	public void setup() {
		this.lS = new DoubleLinkedListImpl<String>();
	    this.lSABC=new DoubleLinkedListImpl<String>("A", "B", "C");
	    this.lSABCDE=new DoubleLinkedListImpl<String>("A", "B", "C", "D", "E");
	}
	
	@Test
	public void testToStringVacio(){
		Assert.assertEquals(lS.toString(),"[]");		
	}
	
	@Test
	public void testToStringNoVacio(){
		Assert.assertEquals(lSABC.toString(),"[A, B, C]");		
	}
	
	@Test
	public void testConstructorConLista(){
		DoubleLinkedListImpl<String> nueva= new DoubleLinkedListImpl<String>(lSABCDE);
		Assert.assertEquals("[A, B, C, D, E]", nueva.toString());
	}
	
	@Test
	public void testForwardIt() {
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.iterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertFalse(i.hasNext());
	    Assert.assertEquals("[A, B, C, D]", lS.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testForwardItException() {
		Iterator<String> i = lS.iterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testForwardItRemove() {
		
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.iterator();
		i.remove();
		
	}
	
	@Test
	public void testReverseIterator() {
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.reverseIterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertFalse(i.hasNext());
	    Assert.assertEquals("[A, B, C, D]", lS.toString());
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testReverseIteratorRemove() {
		
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.reverseIterator();
		i.remove();	
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testReverseIteratorException() {
		Iterator<String> i = lS.reverseIterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
	
	@Test
	public void testOddAndEvenItOk() {
		lS = new DoubleLinkedListImpl<>("A", "B", "C", "D", "E");
		Iterator<String> i = lS.oddAndEvenIterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("E", i.next());
		Assert.assertFalse(i.hasNext());
	    Assert.assertEquals("[A, B, C, D, E]", lS.toString());
	}
	
	@Test 
	public void testOddAndEvenItSize1() {
		
		String t1 = "Soyeon";
		
		
		lS.addFirst(t1);
		Iterator<String> i = lS.oddAndEvenIterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("Soyeon", i.next());
		Assert.assertFalse(i.hasNext());
	
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testOddAndEvenException() {
		Iterator<String> i = lS.oddAndEvenIterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testOddAndEvenIteratorRemove() {
		
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.oddAndEvenIterator();
		i.remove();	
	}
	
	@Test
	public void testSize() {
		
		assertEquals(lS.size(), 0);
		assertEquals(lSABC.size(), 3);
		assertEquals(lSABCDE.size(), 5);
	}
	
	@Test
	public void testEmptyTrue() {
		
		assertTrue(lS.isEmpty());
	}
	
	@Test
	public void testEmptyFalse() {
		
		assertFalse(lSABC.isEmpty());
	}
	
	@Test
	public void testAddFirstEmpty() {
		
		String t1 = "Soyeon";
		assertTrue(lS.isEmpty());
		lS.addFirst(t1);
		assertEquals(lS.toString(), "[Soyeon]");
	}
	
	@Test
	public void testAddFirstNotEmpty() {
		
		String t1 = "Soyeon";
		
		lSABC.addFirst(t1);
		assertEquals(lSABC.toString(), "[Soyeon, A, B, C]");
	}

	@Test
	public void testAddLastEmpty() {
		
		String t1 = "Soyeon";
		assertTrue(lS.isEmpty());
		lS.addLast(t1);
		assertEquals(lS.toString(), "[Soyeon]");
		
	}
	
	@Test
	public void testAddLastNotEmpty() {
		
		String t1 = "Soyeon";
		
		lSABC.addLast(t1);
		assertEquals(lSABC.toString(), "[A, B, C, Soyeon]");
	}
	
	@Test
	public void testAddAtPosPosMayorQueSize() {
		
		String t1 = "Soyeon";
		
		lSABC.addAtPos(t1,4);
		assertEquals(lSABC.toString(), "[A, B, C, Soyeon]");
		
	}
	
	@Test
	public void testAddAtPosOk() {
		
		String t1 = "Soyeon";
		
		lSABC.addAtPos(t1,2);
		assertEquals(lSABC.toString(), "[A, Soyeon, B, C]");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddAtPosInvalid() {
		
		String t1 = "Soyeon";
		
		lSABC.addAtPos(t1,0);
	}
	
	@Test
	public void testAddNTimes() {
		
		String t1 = "Soyeon";
		
		lSABC.addNTimes(t1,2);
		assertEquals(lSABC.toString(), "[A, B, C, Soyeon, Soyeon]");
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetElemInsuficientSize() {
		
		lS.getElem(1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetElemInvalidPos() {
		
		lSABC.getElem(0);
	}
	
	@Test
	public void testGetElemOk() {
		
		assertEquals(lSABC.getElem(2), "B");
		assertEquals(lSABC.getElem(1), "A");
		assertEquals(lSABCDE.getElem(5), "E");
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetElemInsuficientSize() {
		
		String t1 = "Soyeon";
		
		lSABC.setElem(t1, 8);
	}
	
	@Test
	public void testSetElemOk() {
		
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		String t1 = "Soyeon";
		
		assertEquals("C",lS.getElem(3));
		lS.setElem(t1, 3);
		assertEquals("Soyeon",lS.getElem(3));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testIndexOfElemNotFound() {
		
		lSABC.indexOf("E");	
	}
	
	@Test 
	public void testIndexOfElemOk(){

		assertEquals(2,lSABC.indexOf("B"));
		assertEquals(3,lSABC.indexOf("C"));
		assertEquals(1,lSABCDE.indexOf("A"));
		assertEquals(3,lSABCDE.indexOf("C"));
		
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testIndexOfElemPosNotFound() {
		
		lSABC.indexOf("F", 3);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndexOfElemPosInsuficientSize() {
		
		lSABC.indexOf("A", 4);
	}
	
	@Test
	public void testIndexOutOfElemPosOk() {
		
		assertEquals(2,lSABC.indexOf("B", 1));
		assertEquals(3,lSABC.indexOf("C", 1));
		assertEquals(1,lSABCDE.indexOf("A", 1));
		assertEquals(3,lSABCDE.indexOf("C", 3));
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveFirstEmpty() throws EmptyCollectionException {
		
		String t1 = "Soyeon";
		lS.removeFirst(t1);
	}
	
	@Test
	public void testRemoveFirstOk() throws EmptyCollectionException {
		
		assertEquals("A", lSABC.removeFirst("A"));
		assertEquals("C",lSABCDE.removeFirst("C"));
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveAllEmpty() throws EmptyCollectionException {
		
		String t1 = "Soyeon";
		lS.removeAll(t1);
	}
	
	@Test
	public void testRemoveAllOk() throws EmptyCollectionException {
		
		lS = new DoubleLinkedListImpl<String>("A", "B", "B");
		
		assertEquals("B", lS.removeAll("B"));
		assertEquals("[A]", lS.toString());
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastEmpty() throws EmptyCollectionException {
		
		lS.removeLast();
	}
	
	@Test
	public void testRemoveLast() throws EmptyCollectionException {
		
		assertEquals("C", lSABC.removeLast());
		assertEquals("E", lSABCDE.removeLast());
		
	}
	
	@Test
	public void testReverse() {
		
		lSABC.reverse();
		assertEquals("[C, B, A]", lSABC.toString());
		lSABCDE.reverse();
		assertEquals("[E, D, C, B, A]", lSABCDE.toString());
	}
	
	
	@Test
	public void testInterlace() {
		
		lS = new DoubleLinkedListImpl<String>("M", "A", "N", "U");
		DoubleLinkedListImpl<String> jin =  new DoubleLinkedListImpl<String>("I", "R", "E", "N", "E");
		lSABC.interlace(lS);
		lSABCDE.interlace(jin);
		
		assertEquals("[A, M, B, A, C, N, U]", lSABC.toString());
		assertEquals("[A, I, B, R, C, E, D, N, E, E]", lSABCDE.toString());

	}
	
	@Test
	public void testIsSublistEmpty() {
		
		assertEquals(1, lSABC.isSubList(lS));
	}
	
	@Test
	public void testIsSublistNotFound() {
		
		lS = new DoubleLinkedListImpl<String>("M", "A", "N", "U");
		lSABCDE = new DoubleLinkedListImpl<>("A", "C");
		DoubleLinkedListImpl<String> jin =  new DoubleLinkedListImpl<String>("A", "B", "A", "B", "C");
		DoubleLinkedListImpl<String> suga =  new DoubleLinkedListImpl<String>("B", "A","X");
		
		assertEquals(-1, lSABC.isSubList(lS));
		assertEquals(-1, lSABC.isSubList(lSABCDE));
		assertEquals(-1, jin.isSubList(suga));
	}
	
	
	@Test
	public void testIsSublistOk() {
		
		lS = new DoubleLinkedListImpl<String>("A", "B");
		DoubleLinkedListImpl<String> manu =  new DoubleLinkedListImpl<String>("B", "C");
		DoubleLinkedListImpl<String> dona =  new DoubleLinkedListImpl<String>("E");
		DoubleLinkedListImpl<String> lisa =  new DoubleLinkedListImpl<String>("A", "B", "A", "B", "C", "X", "A");
		DoubleLinkedListImpl<String> rose =  new DoubleLinkedListImpl<String>("B", "C", "X");
		DoubleLinkedListImpl<String> jin =  new DoubleLinkedListImpl<String>("A", "B", "A", "B", "C");
		DoubleLinkedListImpl<String> suga =  new DoubleLinkedListImpl<String>("B", "A");
		DoubleLinkedListImpl<String> jhope =  new DoubleLinkedListImpl<String>("A", "B", "A", "B");
		DoubleLinkedListImpl<String> jimin =  new DoubleLinkedListImpl<String>("A", "B");
		DoubleLinkedListImpl<String> jk =  new DoubleLinkedListImpl<String>("A");
		DoubleLinkedListImpl<String> rm =  new DoubleLinkedListImpl<String>("C");
		
		
		
		assertEquals(1, lSABC.isSubList(lS));
		assertEquals(2, lSABC.isSubList(manu));
		assertEquals(5,lSABCDE.isSubList(dona));
		assertEquals(3,lSABCDE.isSubList(rm));
		assertEquals(1,lSABCDE.isSubList(jk));
		
		assertEquals(2,jin.isSubList(suga));
		assertEquals(1,jhope.isSubList(jimin));
		assertEquals(4,lisa.isSubList(rose));
		
	}
	
	
}