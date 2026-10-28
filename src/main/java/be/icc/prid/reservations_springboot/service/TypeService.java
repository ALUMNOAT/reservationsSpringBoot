package be.icc.prid.reservations_springboot.service;

import be.icc.prid.reservations_springboot.model.Type;
import be.icc.prid.reservations_springboot.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    @Autowired
    private TypeRepository repository;

    public List<Type> getAll() {
        List<Type> types = new ArrayList<>();
        repository.findAll().forEach(types::add);
        return types;
    }

    public Type getType(String id) {
        Long indice = (long) Integer.parseInt(id);
        Optional<Type> type = repository.findById(indice);
        return type.orElse(null);
    }

    public void addType(Type type) {
        repository.save(type);
    }

    public void deleteType(String id) {
        Long indice = (long) Integer.parseInt(id);
        repository.deleteById(indice);
    }
}
