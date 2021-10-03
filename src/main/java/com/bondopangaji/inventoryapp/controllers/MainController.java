/*
 * The MIT License
 *
 * Copyright 2021 Bondo Pangaji.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.bondopangaji.inventoryapp.controllers;


import com.bondopangaji.inventoryapp.interfaces.InventoryInterface;
import com.bondopangaji.inventoryapp.models.InventoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Bondo Pangaji
 */

@Controller
public class MainController {

    @Autowired
    private InventoryInterface inventoryInterface;
    
    
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/inventory/list")
    public String getAll(Model model) {
        model.addAttribute("list", inventoryInterface.getAll());
        return "inventory/list";
    }
    
    @GetMapping("/inventory/add")
    public String inventoryAdd(Model model) {
        InventoryModel inventory = new InventoryModel();
        model.addAttribute("inventory", inventory);
        
        return "inventory/add";
    }
    
    @PostMapping("/inventory/storecreate")
    public String storeCreate(@ModelAttribute("inventory") InventoryModel inventory) {
        inventoryInterface.store(inventory);
        return "redirect:/inventory/add";
    }
    
    @GetMapping("/inventory/edit/{id}")
    public String getEdit(@PathVariable(value = "id") long id, Model model) {
        InventoryModel inventory = inventoryInterface.getById(id);

        model.addAttribute("inventory", inventory);
        return "inventory/edit";
    }
    
    @PostMapping("/inventory/storeedit")
    public String storeEdit(@ModelAttribute("inventory") InventoryModel inventory) {
        inventoryInterface.store(inventory);
        return "redirect:/inventory/add";
    }

    @PostMapping("/inventory/{id}/delete")
    public String delete(@PathVariable(value = "id") long id) {
        inventoryInterface.delete(id);
        return "redirect:/inventory/list";
    }
}
