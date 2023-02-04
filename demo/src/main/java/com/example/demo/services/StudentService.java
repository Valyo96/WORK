package com.example.demo.services;

import com.example.demo.constants.PostType;
import com.example.demo.entity.Post;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.demo.constants.ExceptionMessages.userEmailAlreadyExistMessage;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final PostService postService;

    private final Categories categoriesService;

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Student createStudent(Student student){
       return studentRepository.save(student);
    }

    public void updateStudent(Student student){
        List<Student> newStudents= new ArrayList();
    List<Student> students = getAll();
        for (Student nstudent:students) {
            if (!student.getEmail().equals(nstudent.getEmail())){
                newStudents.add(nstudent);
            }
        }
        if(newStudents.stream().anyMatch(s-> s.getEmail().equals(student.getEmail()))){
            throw new AlreadyExistException(userEmailAlreadyExistMessage+student.getEmail());
        }
        studentRepository.save(student);

    }

    public List<Post> getPostsByCategory(String category){
       return postService.findByCategory(category);
    }

    public List<Post> findPostByDate(LocalDate startDate , LocalDate endDate){
      return   postService.findByDate(startDate, endDate);
    }

    public Post findByPostType(PostType label){
        return postService.findByPostType(label);
    }

    public List<Post> findPostByLocation(String location) {
        HashSet<String> locations = categoriesService.getLocations();
        List<Post> matchingPosts = new ArrayList<>();
        for (String loc : locations) {
            if (loc.equalsIgnoreCase(location)) {
                matchingPosts.addAll(postService.findByLocation(loc));
            }
        }
        return matchingPosts;
    }




}
