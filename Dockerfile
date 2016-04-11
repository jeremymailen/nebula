FROM java:8

ADD build/distributions/nebula.tar /

EXPOSE 8080

WORKDIR /nebula
ENTRYPOINT ["bin/nebula"]
