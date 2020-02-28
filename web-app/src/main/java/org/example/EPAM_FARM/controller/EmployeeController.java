package org.example.EPAM_FARM.controller;

import org.example.EPAM_FARM.model.Employee;
import org.example.EPAM_FARM.service.DepartmentService;
import org.example.EPAM_FARM.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeView(
            Model model,
            @PathVariable Integer id
    ) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employee";
    }

    @PostMapping
    public String saveEmployee(
            @RequestParam String name,
            @RequestParam String birthday,
            @RequestParam String salary,
            @RequestParam String departmentName,
            Model model
            ) {


        model.addAttribute("employees", employeeService.findAll());

        if (employeeService.isEmployeeNameAlreadyExists(name)) {
            model.addAttribute("isNameExists","Such employee is already exists!");
            return "employees";
        }else if(!employeeService.isProperFloatValue(salary)) {
            model.addAttribute("wrongFloatValue","Salary value is wrong (e.g. 1034.09)") ;
            return "employees";
        }


        Employee employee = employeeService.returnNewEmployeeWithSetParameters(
                name, birthday, salary
        );
        employeeService.saveEmployee(employee, departmentName);

        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {

        employeeService.deleteDepartment(id);

        return "redirect:/employees";
    }
    @PostMapping("/update/{id}")
    public String updateEmployee (
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam String birthday,
            @RequestParam String salary,
            @RequestParam String departmentName,
            @Valid Employee employee,
            BindingResult bindingResult,
            Model model
    ) {

        model.addAttribute("employees", employeeService.findAll());

        if (employeeService.isEmployeeNameAlreadyExists(name)) {
            model.addAttribute("isNameExists","Such employee is already exists!");
            return "employees";
        }else if(!employeeService.isProperFloatValue(salary)) {
            model.addAttribute("wrongFloatValue","Salary value is wrong (e.g. 1034.09)") ;
            return "employees";
        }

        Employee getEmployee = employeeService.returnNewEmployeeWithSetParameters(
                name,birthday,salary
        );
        employeeService.updateEmployee(id,getEmployee,departmentName);

        return "redirect:/employees";
    }

}
