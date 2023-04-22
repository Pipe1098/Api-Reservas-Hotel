package HotelReservations.service;

import HotelReservations.exception.ApiRequestException;
import HotelReservations.repository.ClienteRepository;
import HotelReservations.dto.ClienteDTO;
import HotelReservations.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteService() {
    }

    public ClienteDTO crear(Cliente cliente) {
        if (cliente==null) {
            throw new ApiRequestException("Los datos del cliente no pueden ser nulos");
        }
            if (validarCliente(cliente)) {
                ClienteDTO clienteDTO = new ClienteDTO(cliente.getCedula(), cliente.getNombre(), cliente.getApellido(), cliente.getCorreoElectronico());
                this.clienteRepository.save(cliente);
                return clienteDTO;
            } else {
                throw new ApiRequestException("Cedula no numerica o el nombre o el apellido están vacíos o son nulos");
            }
        }


    public boolean validarCliente(Cliente cliente) {
        if (cliente.getCedula() == null || !cliente.getCedula().toString().matches("^\\d{10}$")) {
            // La cédula no es numérica o es nula
            return false;
        }

        if (cliente.getNombre() == null || cliente.getNombre().isEmpty() || cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
            // El nombre o el apellido están vacíos o son nulos
            return false;
        }

        return true;
    }

    public List<ClienteDTO> crearClientes() {
       this.clienteRepository.save(new Cliente(123467L,"Carlos","Perez","CRA 40 45",21,"example@hotmail.com"));

        this.clienteRepository.save(new Cliente(12661L,"Andres","Correa","CRA 20 70",30,"Carlos@hotmail.com"));
        return clienteRepository.findAll().
                stream()
                .map(cliente ->new ClienteDTO(
                        cliente.getCedula(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getCorreoElectronico()))
                .collect(Collectors.toList());
    }
}
