package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    private List<Greeting> ListGreeting = new ArrayList<>();

    @GetMapping("/greet/init")
    public String greetInit(){

        for (int i = 0; i < 5; i++){
            ListGreeting.add(new Greeting(counter.incrementAndGet(), "Name" + i, 1990 ));
        }
        return "Great initial";
    }

    @RequestMapping("/greet/list")
    public List<Greeting> getListGreeting(){
        return ListGreeting;
    }

    @GetMapping("/greet/{id}")
    public Greeting getGreetById(@PathVariable(value = "id" ) Integer id){
        Greeting forReturn = null;
        for (int i = 0; i <ListGreeting.size(); i++){
            if (ListGreeting.get(i).getId() == id){
                    forReturn = ListGreeting.get(i);
            }
        }
        return forReturn;
    }



    @PostMapping("/greet/create")
    public String createGreating(@Valid @RequestBody Greeting greeting){
        Greeting forCreating = new Greeting(counter.incrementAndGet(), greeting.getContent(), greeting.getYear());

        ListGreeting.add(forCreating);

        return "created" + forCreating.getId();
    }



    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name), 1990);
    }
}
