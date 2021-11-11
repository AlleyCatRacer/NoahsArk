package Server.SearchItems;

import Model.SearchItems.Facility;
import Model.SearchItems.SearchItemList;
import Model.SearchItems.Service;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class SearchItemListTest
{

  private SearchItemList zero;

  private SearchItemList oneS;
  private SearchItemList many1S;
  private SearchItemList many2S;

  private SearchItemList oneF;
  private SearchItemList many1F;
  private SearchItemList many2F;

  @BeforeEach void setUp() {

    //set up for services
    zero = new SearchItemList();

    oneS = new SearchItemList();
    Service oneService = new Service("one", "two", "three", "four");
    oneS.addService(oneService);

    many1S = new SearchItemList();
    Service service1 = new Service("a", "b", "c", "d");
    Service service2 = new Service("b", "f", "gpost", "h");
    Service service3 = new Service("c", "j", "k", "l");
    many1S.addService(service1);
    many1S.addService(service2);
    many1S.addService(service3);

    many2S = new SearchItemList();
    Service service4 = new Service("d", "n", "opost", "p");
    Service service5 = new Service("e", "s", "t", "q");
    many2S.addService(service1);
    many2S.addService(service2);
    many2S.addService(service3);
    many2S.addService(service4);
    many2S.addService(service5);

    //set up for facilities
    oneF = new SearchItemList();
    Facility oneFac = new Facility("one", "two", "three", "four");
    oneF.addFacility(oneFac);

    many1F = new SearchItemList();
    Facility f1 = new Facility("a", "b", "c", "d");
    Facility f2 = new Facility("b", "gpost", "f", "h");
    Facility f3 = new Facility("c", "j", "k", "l");
    many1F.addFacility(f1);
    many1F.addFacility(f2);
    many1F.addFacility(f3);

    many2F = new SearchItemList();
    Facility f4 = new Facility("d", "opost", "h", "p");
    Facility f5 = new Facility("e", "s", "t", "q");
    many2F.addFacility(f1);
    many2F.addFacility(f2);
    many2F.addFacility(f3);
    many2F.addFacility(f4);
    many2F.addFacility(f5);


  }

  @AfterEach void tearDown()
  {
  }


  //testing the Service related part of the SearchItemList
  //testing SearchItemList constructor
  @Test
  public void testingConstructor() {
    //The constructor does not have any parameters so it always behave in the same way
  }

  //testing getAllServices method
  @Test
  public void gettingEmptyArrayList() {
    assertEquals(0, zero.getAllServices().size());
  }
  @Test
  public void gettingOneElement() {
    assertEquals(1, oneS.getAllServices().size());
    assertEquals("one", oneS.getAllServices().get(0).getTitle());
  }
  @Test
  public void gettingManyElementsEP1() {
    assertEquals(3, many1S.getAllServices().size());
    assertEquals("a", many1S.getAllServices().get(0).getTitle());
    assertEquals("b", many1S.getAllServices().get(1).getTitle());
    assertEquals("c", many1S.getAllServices().get(2).getTitle());
  }
  @Test
  public void gettingManyElementsEP2() {
    assertEquals(5, many2S.getAllServices().size());
    assertEquals("a", many2S.getAllServices().get(0).getTitle());
    assertEquals("b", many2S.getAllServices().get(1).getTitle());
    assertEquals("c", many2S.getAllServices().get(2).getTitle());
    assertEquals("d", many2S.getAllServices().get(3).getTitle());
    assertEquals("e", many2S.getAllServices().get(4).getTitle());
  }
  @Test
  public void gettingElementsBoundaries() {
    //there are no boundaries in this method
  }
  @Test
  public void gettingElementsExceptions() {
    //no exceptions can be thrown in this method
  }

  //testing getServiceBySearch method
  @Test
  public void gettingServiceFromEmptyArrayList() {
    //assertThrows(IllegalArgumentException.class,()-> zero.getServiceBySearch("a"));

    //edit: Jody, 26/05 - method now returns empty string or searchAll() cannot finish searching
    assertTrue(zero.getServiceBySearch("a").isEmpty());
  }
  @Test
  public void gettingOneService() {
    assertEquals("one", oneS.getServiceBySearch("one").get(0).getTitle());
  }
  @Test
  public void gettingManyServices() {
    assertEquals(2, many2S.getServiceBySearch("post").size());
    assertEquals("b", many2S.getServiceBySearch("post").get(0).getTitle());
    assertEquals("d", many2S.getServiceBySearch("post").get(1).getTitle());
  }
  @Test
  public void gettingServiceBoundaries() {
    //there are no boundaries in this method
  }
  @Test
  public void gettingServiceExceptions() {
   // assertThrows(IllegalArgumentException.class,()-> many2S.getServiceBySearch("X"));

    //edit: Jody, 26/05 - method now returns empty string or searchAll() cannot finish searching
    assertTrue(many2S.getServiceBySearch("X").isEmpty());
  }
  //testing addService method
  @Test
  public void addingNoService() {
    //adding no service does not serve a purpose
  }
  @Test
  public void addingOneService() {
    Service test = new Service("test1", "test2", "test3", "test4");
    zero.addService(test);
    assertEquals(1, zero.getAllServices().size());
    assertEquals("test1", zero.getAllServices().get(0).getTitle());
  }
  @Test
  public void addingNullService() {
    assertThrows(IllegalArgumentException.class,()-> zero
        .addService(new Service(null, null, null, null)));
  }
  @Test
  public void addingMultipleServices() {
    //used in BeforeEach method
  }
  @Test
  public void addingServiceBoundaries() {
    //this method has no boundaries
  }
  @Test
  public void addingServiceExceptions() {
    //tested in addingNullService
  }

  //testing removeServiceByTitle method
  @Test
  public void removingFromEmptyList() {
    assertThrows(IllegalArgumentException.class,()-> zero.removeServiceByTitle("X"));
  }
  @Test
  public void removingOneElement() {
    oneS.removeServiceByTitle("one");
    assertEquals(0, oneS.getAllServices().size());
  }
  @Test
  public void removingNullElement() {
    assertThrows(IllegalArgumentException.class,()-> oneS.removeServiceByTitle(null));
  }
  @Test
  public void removingManyElementsEP1() {
    many1S.removeServiceByTitle("a");
    many1S.removeServiceByTitle("c");
    assertEquals(1, many1S.getAllServices().size());
    assertEquals("b", many1S.getAllServices().get(0).getTitle());
  }
  @Test
  public void removingManyElementsEP2() {
    many2S.removeServiceByTitle("a");
    many2S.removeServiceByTitle("c");
    many2S.removeServiceByTitle("e");
    assertEquals(2, many2S.getAllServices().size());
    assertEquals("b", many2S.getAllServices().get(0).getTitle());
    assertEquals("d", many2S.getAllServices().get(1).getTitle());
  }
  @Test
  public void removingElementsBoundaries() {
    //no boundaries in this method
  }
  @Test
  public void removingElementExceptions() {
    //tested in removingFromEmptyList and removingNullElement unit tests
  }

  //testing the Facility related part of the SearchItemList
  //testing SearchItemList constructor


  //testing getAllFacilities method
  @Test
  public void gettingEmptyArrayListFacility() {
    assertEquals(0, zero.getAllFacilities().size());
  }
  @Test
  public void gettingOneElementFacility() {
    assertEquals(1, oneF.getAllFacilities().size());
    assertEquals("one", oneF.getAllFacilities().get(0).getTitle());
  }
  @Test
  public void gettingManyElementsEP1Facility() {
    assertEquals(3, many1F.getAllFacilities().size());
    assertEquals("a", many1F.getAllFacilities().get(0).getTitle());
    assertEquals("b", many1F.getAllFacilities().get(1).getTitle());
    assertEquals("c", many1F.getAllFacilities().get(2).getTitle());
  }
  @Test
  public void gettingManyElementsEP2Facility() {
    assertEquals(5, many2F.getAllFacilities().size());
    assertEquals("a", many2F.getAllFacilities().get(0).getTitle());
    assertEquals("b", many2F.getAllFacilities().get(1).getTitle());
    assertEquals("c", many2F.getAllFacilities().get(2).getTitle());
    assertEquals("d", many2F.getAllFacilities().get(3).getTitle());
    assertEquals("e", many2F.getAllFacilities().get(4).getTitle());
  }
  @Test
  public void gettingElementsBoundariesFacility() {
    //there are no boundaries in this method
  }
  @Test
  public void gettingElementsExceptionsFacility() {
    //no exceptions can be thrown in this method
  }

  //testing getFacilityBySearch method
  @Test
  public void gettingFacilityFromEmptyArrayList() {
   // assertThrows(IllegalArgumentException.class,()-> zero.getFacilityBySearch("a"));

    //edit: Jody, 26/05 - method now returns empty string or searchAll() cannot finish searching
    assertTrue(zero.getFacilityBySearch("a").isEmpty());
  }
  @Test
  public void gettingOneFacility() {
    assertEquals("one", oneF.getFacilityBySearch("one").get(0).getTitle());
  }
  @Test
  public void gettingManyFacilities() {
    assertEquals(2, many2F.getFacilityBySearch("post").size());
    assertEquals("b", many2F.getFacilityBySearch("post").get(0).getTitle());
    assertEquals("d", many2F.getFacilityBySearch("post").get(1).getTitle());
  }
  @Test
  public void gettingFacilityBoundaries() {
    //there are no boundaries in this method
  }
  @Test
  public void gettingFacilityExceptions() {
    //assertThrows(IllegalArgumentException.class,()-> many2F.getFacilityBySearch("X"));

    //edit: Jody, 26/05 - method now returns empty string or searchAll() cannot finish searching
    assertTrue(many2F.getFacilityBySearch("X").isEmpty());
  }
  //testing addFacility method
  @Test
  public void addingNoFacility() {
    //adding no service does not serve a purpose
  }
  @Test
  public void addingOneFacility() {
    Facility test = new Facility("test1", "test2", "test3", "test4");
    zero.addFacility(test);
    assertEquals(1, zero.getAllFacilities().size());
    assertEquals("test1", zero.getAllFacilities().get(0).getTitle());
  }
  @Test
  public void addingNullFacility() {
    assertThrows(IllegalArgumentException.class,()-> zero.addFacility(new Facility(null, null, null, null)));
  }
  @Test
  public void addingMultipleFacilities() {
    //used in BeforeEach method
  }
  @Test
  public void addingFacilityBoundaries() {
    //this method has no boundaries
  }
  @Test
  public void addingFacilityExceptions() {
    //tested in addingNullService
  }

  //testing removeFacilityByTitle method
  @Test
  public void removingFromEmptyListFacility() {
    assertThrows(IllegalArgumentException.class,()-> zero.removeFacilityByName("X"));
  }
  @Test
  public void removingOneElementFacility() {
    oneF.removeFacilityByName("one");
    assertEquals(0, oneF.getAllFacilities().size());
  }
  @Test
  public void removingNullElementFacility() {
    assertThrows(IllegalArgumentException.class,()-> oneF.removeFacilityByName(null));
  }
  @Test
  public void removingManyElementsEP1Facility() {
    many1F.removeFacilityByName("a");
    many1F.removeFacilityByName("c");
    assertEquals(1, many1F.getAllFacilities().size());
    assertEquals("b", many1F.getAllFacilities().get(0).getTitle());
  }
  @Test
  public void removingManyElementsEP2Facility() {
    many2F.removeFacilityByName("a");
    many2F.removeFacilityByName("c");
    many2F.removeFacilityByName("e");
    assertEquals(2, many2F.getAllFacilities().size());
    assertEquals("b", many2F.getAllFacilities().get(0).getTitle());
    assertEquals("d", many2F.getAllFacilities().get(1).getTitle());
  }
  @Test
  public void removingElementsBoundariesFacility() {
    //no boundaries in this method
  }
  @Test
  public void removingElementExceptionsFacility() {
    //tested in removingFromEmptyList and removingNullElement unit tests
  }

}