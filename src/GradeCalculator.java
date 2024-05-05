public interface GradeCalculator {
    default float calculateGrade(Course course){ //grade calculator, idk just made it
        //get the weighted grade
        return ( ( ( (course.getGrade() / 100) * 50 ) + 50 ) * (course.getUnits()/100));
    }
}
