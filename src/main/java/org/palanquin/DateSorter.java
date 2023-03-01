package org.palanquin;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateSorter implements IDateSorter {
    @Override
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        List<LocalDate> datesContainsRInMonth = filterDatesContainingLetterInMonth(unsortedDates, "r");
        List<LocalDate> datesNotContainsRInMonth = getDifferenceOfDateLists(unsortedDates, datesContainsRInMonth);

        datesContainsRInMonth.sort(Comparator.naturalOrder());
        datesNotContainsRInMonth.sort(Comparator.reverseOrder());

        return Stream.concat(datesContainsRInMonth.stream(), datesNotContainsRInMonth.stream())
                .collect(Collectors.toList());
    }


    private List<LocalDate> filterDatesContainingLetterInMonth(List<LocalDate> unsortedDates, String letter) {
        return unsortedDates.stream()
                .filter(date -> date.getMonth().name().toLowerCase().contains(letter))
                .collect(Collectors.toList());
    }


    private List<LocalDate> getDifferenceOfDateLists(List<LocalDate> first, List<LocalDate> second) {
        first.removeAll(second);
        return first;
    }
}
