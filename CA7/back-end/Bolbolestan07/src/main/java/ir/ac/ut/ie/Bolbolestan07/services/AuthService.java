package ir.ac.ut.ie.Bolbolestan07.services;

import ir.ac.ut.ie.Bolbolestan07.controllers.domain.Bolbolestan.Bolbolestan;
import ir.ac.ut.ie.Bolbolestan07.controllers.domain.Bolbolestan.Student.Student;
import ir.ac.ut.ie.Bolbolestan07.controllers.domain.Bolbolestan.exeptions.BolbolestanStudentNotFoundError;
import ir.ac.ut.ie.Bolbolestan07.controllers.models.Login;
import ir.ac.ut.ie.Bolbolestan07.controllers.models.SignUp;
import ir.ac.ut.ie.Bolbolestan07.exceptions.ForbiddenException;
import ir.ac.ut.ie.Bolbolestan07.repository.BolbolestanRepository;
import org.apache.commons.codec.digest.DigestUtils;

public class AuthService {
    public static Student authUser(Login login) throws Exception{
        System.out.println("in auth user");
        if(login.getEmail() == null || login.getEmail().length() == 0 ||
            login.getPassword() == null || login.getPassword().length() == 0)
            throw new ForbiddenException("Fields most have values");
        Student student = BolbolestanRepository.getInstance().getStudentByEmail(login.getEmail());
        if (student != null) {
            System.out.println(String.format("login password = %s, student password = %s", login.getPassword(), student.getPassword()));
            if (!student.getPassword().equals(DigestUtils.sha256Hex(login.getPassword().getBytes()))){
                throw new Exception("passwords does not match");
            }
            return student;
        }
        else
            throw new BolbolestanStudentNotFoundError();
    }

    public static boolean isStudentInDB(String email) throws Exception {
        System.out.println("in validate student");
        if(email == null || email.length() == 0)
            throw new ForbiddenException("Field must have values");
        Student student = BolbolestanRepository.getInstance().getStudentByEmail(email);
        if (student != null) 
            return true;
        else
            throw new BolbolestanStudentNotFoundError();
    }

    public static void signUpUser(SignUp signUpData) throws Exception {
        System.out.println("in auth signup user");
        Student student = BolbolestanRepository.getInstance().getStudentByEmail(signUpData.getEmail());
        if (student != null)
            throw new Exception("email already exists");
        try {
            BolbolestanRepository.getInstance().addNewStudent(signUpData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
