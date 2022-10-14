package Service;

import Model.Room;
import Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAll() {
        return roomRepository.getAll();
    }

    public Optional<Room> getRoom(int id) {
        return roomRepository.getRoom(id);
    }

    public Room save(Room room) {
        if (room.getId() == null) {
            return roomRepository.save(room);
        } else {
            Optional<Room> roomEncontrado = getRoom(room.getId());
            if (roomEncontrado.isEmpty()) {
                return roomRepository.save(room);
            } else {
                return room;
            }
        }
    }

    public Room update(Room room) {
        if (room.getId() != null) {
            Optional<Room> roomEncontrado = getRoom(room.getId());
            if (!roomEncontrado.isEmpty()) {
                if (room.getName() != null) {
                    roomEncontrado.get().setName(room.getName());
                }
                if (room.getBrand() != null) {
                    roomEncontrado.get().setBrand(room.getBrand());
                }
                if (room.getYear() != null) {
                    roomEncontrado.get().setYear(room.getYear());
                }
                if (room.getDescription() != null) {
                    roomEncontrado.get().setDescription(room.getDescription());
                }
                if (room.getCategory() != null) {
                    roomEncontrado.get().setCategory(room.getCategory());

                }
                return roomRepository.save(roomEncontrado.get());
            }
        }
        return room;
    }
    public boolean delete(int id) {
        Boolean respuesta = getRoom(id).map(elemento ->{
            roomRepository.delete(elemento);
            return true;
        }).orElse(false);
        return respuesta;
    }
}

