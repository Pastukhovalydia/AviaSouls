package ru.netology;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.manager.AviaSouls;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AviaSoulsTest {
    private AviaSouls aviaSouls;

    @BeforeEach
    public void setUp() {
        aviaSouls = new AviaSouls();
        aviaSouls.add(new Ticket("Moscow", "Paris", 100, 10, 12));
        aviaSouls.add(new Ticket("London", "Berlin", 200, 15, 18));
        aviaSouls.add(new Ticket("Moscow", "Berlin", 150, 14, 16));
    }


    //при поиске билетов существующего маршрута возвращается массив с соответствующими билетами
    @Test
    public void testSearch_ExistingTickets_ReturnsMatchingTickets() {
        Ticket[] result = aviaSouls.search("Moscow", "Berlin");

        Ticket[] expected = {new Ticket("Moscow", "Berlin", 150, 14, 16)};
        Assertions.assertArrayEquals(expected, result);
    }

    //при поиске билетов для несуществующего маршрута возвращается пустой массив
    @Test
    public void testSearch_NonExistingTickets_ReturnsEmptyArray() {
        Ticket[] result = aviaSouls.search("London", "Paris");

        Ticket[] expected = {};
        Assertions.assertArrayEquals(expected, result);
    }

    //при поиске ровно одного билета возвращается массив, содержащий только этот билет.
    @Test
    public void testSearch_OneMatchingTicket_ReturnsArrayWithOneTicket() {
        Ticket[] result = aviaSouls.search("Moscow", "Paris");

        Ticket[] expected = {new Ticket("Moscow", "Paris", 100, 10, 12)};
        Assertions.assertArrayEquals(expected, result);
    }


    @Test
    public void testSearchAndSortBy_NonExistingTickets_ReturnsEmptyArray() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] result = aviaSouls.searchAndSortBy("London", "Paris", comparator);

        Ticket[] expected = {};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testSearchAndSortBy_ExistingTicket_ReturnsSortedTicket() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] result = aviaSouls.searchAndSortBy("Moscow", "Paris", comparator);

        Ticket[] expected = {new Ticket("Moscow", "Paris", 100, 10, 12)};
        Assertions.assertArrayEquals(expected, result);
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

}