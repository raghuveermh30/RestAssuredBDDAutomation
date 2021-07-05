package Fils;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParese {

    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(Payload.getCourses());
        // Number of courses returned by array

        int count = jsonPath.getInt("courses.size()");
        System.out.println(count);

        //get Purchase amount

        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        //Print title of the First Course

        String firstCourseTitle = jsonPath.get("courses[0].title").toString();
        System.out.println(firstCourseTitle);

        String thirdCourseTitle = jsonPath.get("courses[2].title").toString();
        System.out.println(thirdCourseTitle);

        System.out.println("***********");

        //Print All course titles and their respective Prices

        for (int i = 0; i < count; i++) {
            String courseTitle = jsonPath.get("courses[" + i + "].title").toString();
            String coursePrice = jsonPath.get("courses[" + i + "].price").toString();
            System.out.println(courseTitle);
            System.out.println(coursePrice);
        }

        System.out.println("***************");
        //   Print no of copies sold by RPA Course

        for (int i = 0; i < count; i++) {
            String courseTitle = jsonPath.get("courses[" + i + "].title").toString();
            if (courseTitle.equalsIgnoreCase("RPA")) {
                String rpaCopies = jsonPath.get("courses[" + i + "].copies").toString();
                System.out.println(rpaCopies);
                break;
            }
        }

        System.out.println("***************");
        //Verify if Sum of all Course prices matches with Purchase Amount

        long courseSum=0;
        for(int i =0; i<count;i++){
            int coursePrice = jsonPath.get("courses[" + i + "].price");
            int numOfCopies = jsonPath.get("courses[" + i + "].copies");
            long product = (coursePrice * numOfCopies);
             courseSum = courseSum+product;
            System.out.println("Sum is " +courseSum);
             if(courseSum==purchaseAmount){
                 System.out.println("The Total Sum of the Course and Purchased Amount got matched");
             }
        }


    }


}
