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
package com.bondopangaji.inventoryapp.services;

import com.bondopangaji.inventoryapp.interfaces.InventoryInterface;
import com.bondopangaji.inventoryapp.models.InventoryModel;
import com.bondopangaji.inventoryapp.repositories.InventoryRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bondo Pangaji
 */

@Service
public class InventoryService implements InventoryInterface {

    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Override
    public List<InventoryModel> getAll() {
        return inventoryRepository.findAll();
    }
    
    @Override
    public void store(InventoryModel inventory) {
        
        // in case current date = null
        if (inventory.getCreated_at() == null) {
            Date dateNow = java.util.Calendar.getInstance().getTime();
            inventory.setCreated_at(dateNow);
        }
        
        this.inventoryRepository.save(inventory);
    }
    
    @Override
    public InventoryModel getById(long id) {
        Optional<InventoryModel> optional = inventoryRepository.findById(id);

        if (!optional.isPresent()) {
            throw new RuntimeException(" Inventory not found for id :: " + id);
        }

        InventoryModel inventory = optional.get();
        return inventory;
    }
    
    

    @Override
    public void delete(long id) {
        this.inventoryRepository.deleteById(id);
    }
}