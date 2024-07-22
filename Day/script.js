document.addEventListener("DOMContentLoaded", function() {
    const tableBody = document.querySelector("#daysTable tbody");

    function isLeapYear(year) {
        return (year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0);
    }

    function getDayOfYear(date) {
        const start = new Date(date.getFullYear(), 0, 0);
        const diff = date - start;
        const oneDay = 1000 * 60 * 60 * 24;
        return Math.floor(diff / oneDay);
    }

    function generateLeapYearDays() {
        const year = 2024; // Example leap year
        const leapYearDays = [];
        const date = new Date(year, 0, 1);
        while (date.getFullYear() === year) {
            leapYearDays.push({
                date: date.toDateString(),
                dayOfYear: getDayOfYear(date)
            });
            date.setDate(date.getDate() + 1);
        }
        return leapYearDays;
    }

    function populateTable(days) {
        days.forEach(day => {
            const row = document.createElement("tr");
            const dateCell = document.createElement("td");
            const dayOfYearCell = document.createElement("td");

            dateCell.textContent = day.date;
            dayOfYearCell.textContent = day.dayOfYear;

            row.appendChild(dateCell);
            row.appendChild(dayOfYearCell);
            tableBody.appendChild(row);
        });
    }

    const leapYearDays = generateLeapYearDays();
    populateTable(leapYearDays);
});