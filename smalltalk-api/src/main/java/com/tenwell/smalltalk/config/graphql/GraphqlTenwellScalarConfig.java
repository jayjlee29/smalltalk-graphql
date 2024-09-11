package com.tenwell.smalltalk.config.graphql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.language.StringValue;
import graphql.scalars.ExtendedScalars;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.RuntimeWiring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Configuration(proxyBeanMethods = false)
public class GraphqlTenwellScalarConfig {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

    @Bean
    public GraphQLScalarType localDateTimeScalarType() {
        return GraphQLScalarType.newScalar()
                .name("LocalDateTime")
                .description("Java 8 LocalDateTime as scalar.")
                .coercing(new Coercing<Object, Object>() {
                    @Override
                    public String serialize(final Object dataFetcherResult) {
                        if (dataFetcherResult instanceof LocalDateTime) {
                            return dateTimeFormatter.format((LocalDateTime) dataFetcherResult);
                        } else {
                            throw new CoercingSerializeException("Expected a LocalDate object.");
                        }
                    }

                    @Override
                    public LocalDateTime parseValue(final Object input) {
                        try {
                            if (input instanceof String) {
                                return LocalDateTime.parse((String) input, dateTimeFormatter);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e);
                        }
                    }

                    @Override
                    public LocalDateTime parseLiteral(final Object input) {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDateTime.parse(((StringValue) input).getValue(), formatter);
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }
                }).build();
    }
    @Bean
    public GraphQLScalarType locaDateScalarType() {
        return GraphQLScalarType.newScalar()
                .name("LocalDate")
                .description("Java 8 LocalDateTime as scalar.")
                .coercing(new Coercing<Object, Object>() {
                    @Override
                    public String serialize(final Object dataFetcherResult) {
                        if (dataFetcherResult instanceof LocalDate) {
                            return formatter.format((LocalDate) dataFetcherResult);
                        } else {
                            throw new CoercingSerializeException("Expected a LocalDate object.");
                        }
                    }

                    @Override
                    public LocalDate parseValue(final Object input) {
                        try {
                            if (input instanceof String) {
                                return LocalDate.parse((String) input, formatter);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e
                            );
                        }
                    }

                    @Override
                    public LocalDate parseLiteral(final Object input) {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDate.parse(((StringValue) input).getValue(), formatter);
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }
                }).build();
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        final RuntimeWiringConfigurer runtimeWiringConfigurer = (RuntimeWiring.Builder wiringBuilder) -> {
            wiringBuilder.scalar(locaDateScalarType());
            wiringBuilder.scalar(localDateTimeScalarType());
            wiringBuilder.scalar(ExtendedScalars.LocalTime);
            wiringBuilder.scalar(ExtendedScalars.GraphQLLong);
        };
        return runtimeWiringConfigurer;
    }

    // @Bean
    // public GraphQLScalarType graphqlLong() {
    //     return ExtendedScalars.GraphQLLong;
    // }

}
