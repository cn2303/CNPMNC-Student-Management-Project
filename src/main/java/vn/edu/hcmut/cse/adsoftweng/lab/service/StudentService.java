package vn.edu.hcmut.cse.adsoftweng.lab.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

import java.util.List;

import javax.management.RuntimeErrorException;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }
    public Student getById(String id) {
        return studentRepository.findById(id).orElse(null);
    }
    public List<Student> searchByName(String name){
        return studentRepository.findByNameContainingIgnoreCase(name);
    }
    public Student addStudent(Student student){
        if(student.getId() == null|| student.getId().trim().isEmpty()){
            throw new RuntimeException("Thiếu ID");
        }
        if(studentRepository.existsById(student.getId())){
            throw new RuntimeException("ID đã tồn tại");
        }

        return this.save(student);
    }
    public Student save(Student student) {
        return studentRepository.save(student);
    }
    public void delete(String id) {
        studentRepository.deleteById(id);
    }
}
