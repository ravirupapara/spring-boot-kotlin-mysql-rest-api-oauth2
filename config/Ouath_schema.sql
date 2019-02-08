
drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token MEDIUMBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token MEDIUMBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication MEDIUMBLOB,
  refresh_token VARCHAR(255)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token MEDIUMBLOB,
  authentication MEDIUMBLOB
);
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication MEDIUMBLOB
);
 
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP
);
 
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255),
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation TEXT,
  autoApproveScopes VARCHAR(255)
);


INSERT INTO `kotlin_api_demo`.`users` (`id`, `created_at`, `email`, `is_admin`, `is_super_admin`, `name`, `password`, `phone_no`, `updated_at`) VALUES ('1', '2019-02-08 00:00:00', 'demo@gmail.com', '1', '1', 'Demo', 'test', '9645821366', '2019-02-08 00:00:00');

INSERT INTO `kotlin_api_demo`.`oauth_client_details` (`client_id`, `client_secret`, `scope`, `authorized_grant_types`) VALUES ('clientapp', 'klsoehbd2kxw8ksl2ix', 'read,write,trust', 'password');

