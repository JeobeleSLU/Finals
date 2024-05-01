public interface GradeCalculator {
    default float calculateGrade(Course course){
        //get the weighted grade
        return ( ( ( (course.getGrade() / 100) * 50 ) + 50 ) * course.getUnits());
    }
}
