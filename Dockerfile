FROM vertx/vertx3

ENV VERTICLE_NAME eu.trustdemocracy.ranker.endpoints.App
ENV VERTICLE_FILE target/ranker-1.0-SNAPSHOT.jar


ENV VERTICLE_HOME /usr/verticles

EXPOSE 8080

COPY $VERTICLE_FILE $VERTICLE_HOME/

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec vertx run $VERTICLE_NAME -cp $VERTICLE_HOME/*"]
