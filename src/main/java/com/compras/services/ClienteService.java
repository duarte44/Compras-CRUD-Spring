package com.compras.services;

import com.compras.dto.ClienteDTO;
import com.compras.entities.Cliente;
import com.compras.exceptions.DataIntegrityException;
import com.compras.exceptions.ObjectNotFoundException;
import com.compras.repositories.ClienteRepository;
import com.compras.repositories.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente find(Long id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado ID: " + id + ", tipo: " + Cliente.class.getName()));
    }

    public Cliente insert( Cliente obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);

    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome()); //o newObj que a gente procurou no banco ele foi atualizado com os novos dados obj
        newObj.setTelefone(obj.getTelefone());

    }

    public void delete (Long id) {
        find(id);
        try {
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir esse usuário");
        }
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getTelefone());
    }

}
