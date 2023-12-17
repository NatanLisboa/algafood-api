update oauth_client_details
set authorized_grant_types='password,authorization_code',
web_server_redirect_uri='http://localhost:8080,http://localhost:8080/swagger-ui/oauth2-redirect.html,http://localhost:80,http://localhost:80/swagger-ui/oauth2-redirect.html,https://www.algafoodapi.com.br,https://www.algafoodapi.com.br/swagger-ui/oauth2-redirect.html,http://www.algafood-api:8080,http://www.algafood-api:8080/swagger-ui/oauth2-redirect.html'
where client_id = 'algafood-web';
