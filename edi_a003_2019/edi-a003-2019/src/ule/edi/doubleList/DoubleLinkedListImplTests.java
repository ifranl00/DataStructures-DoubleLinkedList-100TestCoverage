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
		
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		
		lS.indexOf("E");
	}
	
	@Test 
	public void testIndexOfElemOk() {

		assertEquals(2,lSABC.indexOf("B"));
		assertEquals(1,lSABCDE.indexOf("A"));
		
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testIndexOfElemPosNotFound() {
		
		lSABC.indexOf("F", 1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndexOfElemPosInsuficientSize() {
		
		lSABC.indexOf("A", 4);
	}
	
	@Test
	public void testIndexOutOfElemPosOk() {
		
		assertEquals(2,lSABC.indexOf("B", 1));
		assertEquals(1,lSABCDE.indexOf("A", 1));
		assertEquals(1,lSABCDE.indexOf("E", 2));
		
		
	}
	
	
}
