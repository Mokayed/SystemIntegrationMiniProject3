package food.rest;

import food.rest.Entities.Food;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestApplicationTests {
ArrayList<Food> foods;
JSONHandler jsonHandler;

@BeforeEach
void prepBeforeEach() throws IOException, ParseException {
    foods = new ArrayList<Food>();
    this.jsonHandler = new JSONHandler();
    foods = jsonHandler.jsonToArrayList(foods);
}
@AfterEach
void dropData() {
    foods = new ArrayList<Food>();
}

    @Test
    void testJsonFile() throws IOException, ParseException {
        System.out.println("size is: " + foods.size());
        foods = jsonHandler.jsonToArrayList(foods);
        Assert.assertFalse(foods.isEmpty());
    }

    @Test
    void testCategorizedFoods(){
        System.out.println("size is: " + foods.size());
        String category = "coffee";
        foods = jsonHandler.getSpecificFoods(foods, category);
        for (Food food : foods){
            Assert.assertTrue(food.getFood_group().toLowerCase().contains(category.toLowerCase()));
        }
    }

    @Test
    void testFoodsByName(){
        System.out.println("size is: " + foods.size());
        String name = "Peanut";
        foods = jsonHandler.getFoodsByName(foods, name);
        for (Food food : foods){
            Assert.assertTrue(food.getName().toLowerCase().contains(name.toLowerCase()));
        }
    }
    @Test
    void foodById(){
        System.out.println("size is: " + foods.size());
        long id = 1;
        Food food = new Food();
        food = jsonHandler.getFoodById(foods, id);
        System.out.println(food.toString());
        Assert.assertTrue(food.getId() == 1);
    }

}
