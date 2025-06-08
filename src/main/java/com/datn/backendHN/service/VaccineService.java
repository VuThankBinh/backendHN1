package com.datn.backendHN.service;

import com.datn.backendHN.entity.Vaccine;
import com.datn.backendHN.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    @Autowired
    private VaccineRepository vaccineRepository;

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    public Optional<Vaccine> getVaccineById(Long id) {
        return vaccineRepository.findById(id);
    }

    public List<Vaccine> searchVaccines(String name, String manufacturer, Boolean isActive) {
        if (name != null && manufacturer != null && isActive != null) {
            return vaccineRepository.findByNameContainingAndManufacturerContainingAndIsActive(name, manufacturer, isActive);
        } else if (name != null && manufacturer != null) {
            return vaccineRepository.findByNameContainingAndManufacturerContaining(name, manufacturer);
        } else if (name != null && isActive != null) {
            return vaccineRepository.findByNameContainingAndIsActive(name, isActive);
        } else if (manufacturer != null && isActive != null) {
            return vaccineRepository.findByManufacturerContainingAndIsActive(manufacturer, isActive);
        } else if (name != null) {
            return vaccineRepository.findByNameContaining(name);
        } else if (manufacturer != null) {
            return vaccineRepository.findByManufacturerContaining(manufacturer);
        } else if (isActive != null) {
            return vaccineRepository.findByIsActive(isActive);
        }
        return vaccineRepository.findAll();
    }

    public Vaccine createVaccine(Vaccine vaccine) {
        if (vaccineRepository.existsByName(vaccine.getName())) {
            throw new RuntimeException("Vaccine with this name already exists");
        }
        return vaccineRepository.save(vaccine);
    }

    public Vaccine updateVaccine(Long id, Vaccine vaccineDetails) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found"));
        
        vaccine.setName(vaccineDetails.getName());
        vaccine.setManufacturer(vaccineDetails.getManufacturer());
        vaccine.setDescription(vaccineDetails.getDescription());
        vaccine.setNumberOfDoses(vaccineDetails.getNumberOfDoses());
        vaccine.setIntervalBetweenDoses(vaccineDetails.getIntervalBetweenDoses());
        vaccine.setContraindications(vaccineDetails.getContraindications());
        vaccine.setSideEffects(vaccineDetails.getSideEffects());
        
        return vaccineRepository.save(vaccine);
    }

    public void deleteVaccine(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found"));
        vaccine.setIsActive(false);
        vaccineRepository.save(vaccine);
    }
} 