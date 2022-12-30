package com.compras.services;

import com.compras.dto.ClienteDTO;
import com.compras.dto.ComprasDTO;
import com.compras.entities.Cliente;
import com.compras.entities.Compras;
import com.compras.exceptions.DataIntegrityException;
import com.compras.exceptions.ObjectNotFoundException;
import com.compras.repositories.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository repository;

    public List<Compras> findAll() {
        return repository.findAll();
    }

    public Compras find(Long id){
        Optional<Compras> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado ID: " + id + ", tipo: " + Cliente.class.getName()));
    }

    public Compras insert( Compras obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public Compras update(Compras obj){
        Compras newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);

    }

    public void delete (Long id) {
        find(id);
        try {
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir esse usuário");
        }
    }

    private void updateData(Compras newObj, Compras obj) {
        newObj.setItem(obj.getItem()); //o newObj que a gente procurou no banco ele foi atualizado com os novos dados obj
        newObj.setPreco(obj.getPreco());

    }

    public Compras fromDTO(ComprasDTO objDto) {
        return new Compras(objDto.getId(), objDto.getItem(), objDto.getPreco(), objDto.getCliente());
    }
}
