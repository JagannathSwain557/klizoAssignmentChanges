package com.klizo.Assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klizo.Assignment.dto.AuthenticationRequest;
import com.klizo.Assignment.dto.AuthenticationResponse;
import com.klizo.Assignment.dto.LoginDto;
import com.klizo.Assignment.dto.UserDto;
import com.klizo.Assignment.entity.UserEntity;
import com.klizo.Assignment.service.MyUserDetailsService;
import com.klizo.Assignment.service.UserService;
import com.klizo.Assignment.util.UserUtil;

@RequestMapping(value = "/klizo/assignment")
@CrossOrigin("*")
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}
	/*
localhost:9090/klizo/assignment/user/token
*/
	@RequestMapping(value = "/user/token", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	/*
localhost:9090/klizo/assignment/user/save
*/

	@PostMapping(value="/user/save",consumes = { "application/xml", "application/json" })
		public ResponseEntity<UserEntity> create(@RequestBody UserDto userDto) {
		UserEntity userEntity=userService.create(userDto);
		return new ResponseEntity<UserEntity>(userEntity,HttpStatus.OK);
	}
	/*
localhost:9090/klizo/assignment/user/getall
token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4MDA2OTAyNSwiaWF0IjoxNjgwMDMzMDI1fQ.BlmED_Z8LbSpNEtMqy4uSU8c2gpQDdayKJhCMRz3CnY
*/
	@GetMapping("/user/getall")
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(), HttpStatus.OK);
	}
	/*
localhost:9090/klizo/assignment/user/update/{fastName}
token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4MDA2OTAyNSwiaWF0IjoxNjgwMDMzMDI1fQ.BlmED_Z8LbSpNEtMqy4uSU8c2gpQDdayKJhCMRz3CnY
*/
	@PutMapping(value="/user/update/{fastName}",consumes = { "application/xml", "application/json" })
	public ResponseEntity<UserEntity> updateUser(@PathVariable(value = "fastName") String fastName,
			@RequestBody UserDto userDetails)  {
		UserEntity userEntity = userService.updateUser(userDetails, fastName);
		return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
	}
	/*
localhost:9090/klizo/assignment/user/authenticate
This is the normal authenticate by using query for backend call
*/
	@PostMapping("/user/authenticate")
	public ResponseEntity<Object> authUserNamePassword(@RequestBody LoginDto LoginDto) throws Exception{
		
		List<UserEntity> getdata = userService.authenticateUserNamePassword(LoginDto.getUsername(),LoginDto.getPassword());
		if(getdata!=null && getdata.size()>0){
			return new ResponseEntity<Object>(getdata.get(0), HttpStatus.OK);
		}else{
			throw new Exception("Login Failed !!");
		}
	}
	/*
localhost:9090/klizo/assignment/user/delete/{fastName}
token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4MDA2OTAyNSwiaWF0IjoxNjgwMDMzMDI1fQ.BlmED_Z8LbSpNEtMqy4uSU8c2gpQDdayKJhCMRz3CnY
*/
    @DeleteMapping("/user/delete/{fastName}")
    public ResponseEntity<UserEntity> delete(@PathVariable(value="fastName")String fastName){
    	UserEntity userEntity=userService.delete(fastName);
    	return new ResponseEntity<UserEntity>(userEntity,HttpStatus.OK);
    }
	@PostMapping("/user/authentication")
	public ResponseEntity<Object> authUserName(@RequestParam("username") String username) throws Exception{
		
		List<UserEntity> getdata = userService.authenticateUserName(username);
		if(getdata!=null && getdata.size()>0){
			return new ResponseEntity<Object>(getdata.get(0), HttpStatus.OK);
		}else{
			throw new Exception("Login Failed !!");
		}
	}
	
}
