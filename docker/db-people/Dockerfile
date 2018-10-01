FROM postgres:latest

ENV POSTGRES_DB peopledb
ENV POSTGRES_USER people
ENV POSTGRES_PASSWORD people

ADD sql/*.sql /docker-entrypoint-initdb.d/
