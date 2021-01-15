<h1> Steps of creation a simple REST Blog API</h1>
<hr>
<br>
<h3> 1. Create simple starter structure (directories, files)</h3>
<ul>
<li>Create packages (repositories, entities, controllers) - <strong>10 minutes</strong></li>
<li>Create simple mock classes (repositories, entities, controllers)  - <strong>15 minutes</strong></li>
<li>Create endpoints (main) - <strong>20 minutes</strong> <small>(Taken around 1 hour and 20 minutes )</small></li> 
</ul>    
<h5> Total time: 45 minutes</h5>
<h5> DONE - taken around 2 hours</h5>
<br>
<h3> 2. Create simple structure with Spring JPA;</h3>
<ul>
<li>Create content of POJO-classes: User, Comments, etc. - <strong>30 minutes</strong><small>(done in previous step)</small></li>
<li>Link classes to db (also create liquibase changelog) - <strong>40 minutes</strong></li>
<li>Configure application.properties. - <strong>15 minutes</strong></li>
<li>Implement JpaRepositories for our POJO-classes - <strong>30 minutes</strong></li>
</ul>    
<h5> Total time: 1:55</h5>
<h5>DONE - taken around 40 minutes</h5>
<br>
<h3> 3. Configure Spring Security and add jwt token </h3>
<ul>
<li>Implement UserDetails - <strong>30 minutes</strong></li>
<li>Implement UserDetailsService - <strong>30 minutes</strong></li>
<li>Create JwtTokenProvider - <strong>30 minutes</strong></li>
<li>Create JwtTokenFilter - <strong>30 minutes</strong></li>
<li>Create and implement custom UserService - <strong>30 minutes</strong></li>
<li>Add appropriate controllers - <strong>20 minutes</strong></li>
<li>Configure everything with Spring Security - <strong>30 minutes</strong></li>
</ul>    
<h5> Total time: 3:20</h5>
<h5> DONE - taken 1:10</h5>
<br>
<h3> 4. Implement controllers(endpoints) logic</h3>
<ul>
<li>AuthController - <strong>30 minutes</strong></li>
<li>Login - <strong>30 minutes</strong></li>
<li>ArticlesController - <strong>30 minutes</strong></li>
<li>CommentsController - <strong>30 minutes</strong></li>
<li>TagsController - <strong>30 minutes</strong></li>
<li>FilterController - <strong>30 minutes</strong></li>
</ul>    
<h5> Total time: around 3 hours</h5>
<h5> DONE - taken around 16 hours :(</h5>
<br>
<h3> 5. Sending mail</h3>
<ul>
<li>Add and configure MailSender <strong>40 minutes</strong></li>
<li>Add and configure Redis <strong>40 minutes</strong></li>
<li>Add sending mail on registration feature and create appropriate controller <strong>40 minutes</strong></li>
<li>Add reset password feature and edit appropriate controller <strong>40 minutes</strong></li>
</ul>    
<h5> Total time: 2:40 - 3:30 (around)</h5>
<br>
<h3> 6. Tests + Docker</h3>
<ul>
<li>Create 2 UnitTests <strong>50 minutes</strong></li>
<li>Create 2 integration tests <strong>50 minutes</strong></li>
<li>Create some scripts for Docker <strong>40 minutes</strong></li>
<li>Add profiles (dev/prod) <strong>20 minutes</strong></li>
</ul>    
<h5> Total time: around 2:40 - 3:10</h5>