package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.manager.TicketTimeComparator;



public class AviaSoulsTest {
    private AviaSouls aviaSouls;

    @BeforeEach
    public void setUp() {
        aviaSouls = new AviaSouls();
        aviaSouls.add(new Ticket("Moscow", "Paris", 100, 10, 12));
        aviaSouls.add(new Ticket("London", "Berlin", 200, 15, 18));
        aviaSouls.add(new Ticket("Moscow", "Berlin", 150, 14, 16));
    }

    @Test
    public void testSearch_ExistingTickets_ReturnsMatchingTickets() {
        Ticket[] result = aviaSouls.search("Moscow", "Berlin");

        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals("Moscow", result[0].getFrom());
        Assertions.assertEquals("Berlin", result[0].getTo());
        Assertions.assertEquals(150, result[0].getPrice());
        Assertions.assertEquals(14, result[0].getTimeFrom());
        Assertions.assertEquals(16, result[0].getTimeTo());
    }

    @Test
    public void testSearch_NonExistingTickets_ReturnsEmptyArray() {
        Ticket[] result = aviaSouls.search("London", "Paris");

        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void testCompareTo_SamePrice_ReturnsZero() {
        Ticket ticket1 = new Ticket("Moscow", "Paris", 100, 10, 12);
        Ticket ticket2 = new Ticket("London", "Berlin", 100, 15, 18);

        int result = ticket1.compareTo(ticket2);

        Assertions.assertEquals(0, result);
    }

    @Test
    public void testCompareTo_LowerPrice_ReturnsNegativeValue() {
        Ticket ticket1 = new Ticket("Moscow", "Paris", 100, 10, 12);
        Ticket ticket2 = new Ticket("London", "Berlin", 200, 15, 18);

        int result = ticket1.compareTo(ticket2);

        Assertions.assertTrue(result < 0);
    }

    @Test
    public void testCompareTo_HigherPrice_ReturnsPositiveValue() {
        Ticket ticket1 = new Ticket("Moscow", "Paris", 200, 10, 12);
        Ticket ticket2 = new Ticket("London", "Berlin", 100, 15, 18);

        int result = ticket1.compareTo(ticket2);

        Assertions.assertTrue(result > 0);
    }


    @Test
    public void testCompare_SameTimeDifference_ReturnsZero() {
        Ticket ticket1 = new Ticket("Moscow", "Paris", 100, 10, 12);
        Ticket ticket2 = new Ticket("London", "Berlin", 200, 15, 17);

        TicketTimeComparator comparator = new TicketTimeComparator();
        int result = comparator.compare(ticket1, ticket2);

        Assertions.assertEquals(0, result);
    }

    @Test
    public void testCompare_LowerTimeDifference_ReturnsNegativeValue() {
        Ticket ticket1 = new Ticket("Moscow", "Paris", 100, 10, 12);
        Ticket ticket2 = new Ticket("London", "Berlin", 200, 15, 18);

        TicketTimeComparator comparator = new TicketTimeComparator();
        int result = comparator.compare(ticket1, ticket2);

        Assertions.assertTrue(result < 0);
    }

    @Test
    public void testCompare_HigherTimeDifference_ReturnsPositiveValue() {
        Ticket ticket1 = new Ticket("Moscow", "Paris", 100, 10, 12);
        Ticket ticket2 = new Ticket("London", "Berlin", 200, 15, 19);

        TicketTimeComparator comparator = new TicketTimeComparator();
        int result = comparator.compare(ticket1, ticket2);

        Assertions.assertFalse(result > 0);
    }

    @Test
    public void testSearchAndSortBy_NonExistingTickets_ReturnsEmptyArray() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] result = aviaSouls.searchAndSortBy("London", "Paris", comparator);

        Assertions.assertEquals(0, result.length);
    }

}
