# Client

The plugin allows you to configure the Secrets Manager client that it uses to access secrets.

The most common use case is to access secrets in other accounts using [IAM cross-account roles](https://docs.aws.amazon.com/IAM/latest/UserGuide/tutorial_cross-account-with-roles.html).

## Credentials Provider

The plugin supports the following `AWSCredentialsProvider` implementations to authenticate and authorize with Secrets Manager.

*Note: This is not the same thing as a Jenkins `CredentialsProvider`.*

### Default

This uses the standard AWS credentials lookup chain.

### Profile

This allows you to use named AWS profiles (which may then reference IAM roles) from `~/.aws/config`.

```yaml
unclassified:
  awsCredentialsProvider:
    client:
      credentialsProvider:
        profile:
          profileName: "foobar"
```

### STS AssumeRole

This allows you to specify IAM roles inline within Jenkins. You will typically use this when you cannot specify the roles in `~/.aws/config`.

```yaml
unclassified:
  awsCredentialsProvider:
    client:
      credentialsProvider:
        assumeRole:
          roleArn: "arn:aws:iam::111111111111:role/foo"
          roleSessionName: "jenkins"
```

## Endpoint Configuration

You can set the AWS endpoint configuration for the client.

```yaml
unclassified:
  awsCredentialsProvider:
    client:
      endpointConfiguration:
        serviceEndpoint: "http://localhost:4584"
        signingRegion: "us-east-1"
```

## Region

You can set the AWS region for the client. You will typically do this when the target AWS account is in a different region to Jenkins.

```yaml
unclassified:
  awsCredentialsProvider:
    client:
      region: "us-east-1"
```
