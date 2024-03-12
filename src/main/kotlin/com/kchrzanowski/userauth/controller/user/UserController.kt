package com.kchrzanowski.userauth.controller.user
import com.kchrzanowski.userauth.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {


    @PostMapping("/add")
    fun create(@RequestBody userRequest: UserRequest): UserResponse =
            userService.create(userRequest)


    @GetMapping("/all")
    fun listAll(): List<UserResponse> = userService.findAll()

    @GetMapping("/uuid/{uuid}")
    fun findByUUID(@PathVariable uuid: UUID) = userService.findByUUID(uuid)

    @DeleteMapping("/delete/{uuid}")
    fun deleteByUUID(@PathVariable uuid: UUID): ResponseEntity<Boolean> {
        userService.deleteByUUID(uuid)
        return ResponseEntity.noContent().build()

    }

}





