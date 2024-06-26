package com.vaibhav.data.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.vaibhav.data.dao.EmployeeRepo;
import com.vaibhav.data.dao.MenuRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class dataController {
	@Autowired
	EmployeeRepo repo;
//	@Autowired
//	CartRepo cartRepo;
	
	@Autowired
	JdbcTemplate template;
	@Autowired 
	MenuRepo menuRepo;
	String findbyIdsql="select id, item_name as itemName,item_price as itemPrice from menu where id=?";
	
	@RequestMapping("/home")
	public ModelAndView home (Employee user)
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("user",user);
		mv.setViewName("home");
		return mv;
	}
	@RequestMapping("/input")
	public ModelAndView input (Employee user)
	{   
		
	   // repo.save(user);
		ModelAndView mv=new ModelAndView();
		mv.addObject("user",user);
		mv.setViewName("input");
		return mv;
	}
	@RequestMapping("/addUser")
	public ModelAndView addUser (Employee user)
	{   		ModelAndView mv=new ModelAndView();
		int id=user.getId();
		Employee ab=repo.findById(id).orElse(null);
		if(ab!=null)
		{
			mv.addObject("user",user);
			mv.setViewName("alreadyExists");
			return mv;
		}
			
		else {
	    repo.save(user);
		
		mv.addObject("user",user);
		mv.setViewName("add_user");
		}
		return mv;}
	
  

   @RequestMapping("/getUser")
   public ModelAndView getUser( int id)
   {
	   Employee user= repo.findById(id).orElse(null);
	   System.out.println(user.toString());
	   ModelAndView mv=new ModelAndView("showUser");
	   mv.addObject("user",user);
	   return mv;
   
}
   @RequestMapping("/user/{id}")
   @ResponseBody
   public Optional<Employee> users(@PathVariable("id") int id)
   {
	   return repo.findById(id);
   }
   @RequestMapping("/users")
   @ResponseBody
   public List<Employee> users()
   {
	   return repo.findAll();
   }
   @PostMapping("/postUsers")
   @ResponseBody
   public Employee postUser(@RequestBody Employee user)
   {    
	   repo.save(user);
	   return user;
   
   }
   
//   @CrossOrigin(origins = "http://localhost:4200")
   @RequestMapping("/getMenu")
   public List<Menu> getMenu()
   {
	return menuRepo.findAll();
   }
	@RequestMapping(value="/updateMenu",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces="application/json")
	   public void updateMenu(@RequestBody Menu menu) throws InvalidInputException
	   {
		menuRepo.save(menu);
   }
		@RequestMapping(value="/addMenu",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces="application/json")
		   public void addMenu(@RequestBody Menu menu) throws InvalidInputException
		   {
			menuRepo.save(menu);
	   }
		@RequestMapping(value="/deleteMenu",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces="application/json")
		   public void deleteMenu(@RequestBody Menu menu) throws InvalidInputException
		   {
			menuRepo.delete(menu);
		   }
		@RequestMapping(value="/addItemToCart",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces="application/json")
		   public Cart addItemToCart(@RequestBody List<Integer> a) throws InvalidInputException
		   {
			Cart  cart =new Cart();
			List<Menu> menu=new ArrayList<Menu>();
			double itemPrice=0.0;
			for(int i=0;i<a.size();i++)
			{
				Menu ab = template.query(findbyIdsql, new BeanPropertyRowMapper<Menu>(Menu.class),new Object[] {a.get(i)}).get(0);
				System.out.println(ab.getItemName());
				menu.add(ab);
				itemPrice=itemPrice+Integer.valueOf(ab.getItemPrice());
				}
			cart.setMenu(menu);
			cart.setTotalPrice(itemPrice);
			return cart;
         }
}

