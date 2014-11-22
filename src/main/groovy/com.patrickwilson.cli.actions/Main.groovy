/***********************************************************************************************************************
 Licensed to Patrick Wilson Consulting under one
        or more contributor license agreements.  See the NOTICE file
        distributed with this work for additional information
        regarding copyright ownership.  Patrick Wilson Consulting licenses this file
        to you under the Apache License, Version 2.0 (the
        "License"); you may not use this file except in compliance
        with the License.  You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing,
        software distributed under the License is distributed on an
        "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
        KIND, either express or implied.  See the License for the
        specific language governing permissions and limitations
        under the License.
 **********************************************************************************************************************/

package com.patrickwilson.cli.actions;

import com.google.common.collect.ImmutableMap
import javafx.application.Application
import jdk.nashorn.internal.ir.annotations.Immutable
import org.apache.commons.cli.*

/**
 * This is the main class for the CLI application.
 * User: pwilson
 */
public class Main {

    private static final int EXIT_OK = 0;
    private static final int EXIT_INVALID_ARGS = 1;

    private String appName;
//
//    private ]= final Map<String,MercuryAction> actionsMap =
//            ImmutableMap.<String,MercuryAction>builder()
//
//            .put("add-host", new AddHostAction())
//            .put("remove-host", new RemoveHostAction())
//            .put("update-host", new UpdateHostAction())
//            .put("add-network", new AddNetworkAction())
//            .put("updating-network", new UpdateNetworkAction())
//            .put("remove-network", new DeleteNetworkAction())
//            .put("list-networks", new ListNetworksAction())
//            .put("add-ip", new AddIpAddressToNetworkAction())
//            .put("remove-ip", new RemoveIpAddressFromNetworkAction())
//            .put("create-image", new CreateImageAction())
//            .put("delete-image", new DeleteImageAction())
//            .put("search-images", new SearchImagesAction())
//            .put("create-template", new CreateTemplateAction())
//            .put("update-template", new UpdateTemplateAction())
//            .put("delete-template", new DeleteTemplateAction())
//            .put("search-templates", new SearchTemplatesAction())
//            .put("new-instance", new CreateInstanceAction())
//            .put("delete-instance", new RemoveInstanceAction())
//            .put("search-instances", new SearchInstancesAction())

//            .build();
    private final Map<String, ApplicationAction> actionsMap;

    public Main(String appName, Map<String, ApplicationAction> actions) {
        this.actionsMap = actions
        this.appName = appName;
    }


    public void executeApplication(String... args){


        String action = null;
        if (args.length >= 1) {
            action = args[0]
        }

        if (action == "help") {
            printHelp()
            System.exit(EXIT_OK)
        } else {

            if (!this.actionsMap.containsKey(action)) {
                printHelp()
                System.exit(EXIT_OK)
            } else {
                Options factory = new Options();
                ApplicationAction executableAction = this.actionsMap[action]
                executableAction.addCommandlineOption(factory)

                CommandLineParser parser = new BasicParser();

                CommandLine cmd
                try {
                    cmd = parser.parse(factory, args, false)
                } catch (UnrecognizedOptionException e) {
                    println("Invalid Command: $e.message")
                    HelpFormatter help = new HelpFormatter()
                    help.printHelp("vm-utils " + action, factory)
                    System.exit(EXIT_INVALID_ARGS)
                }  catch (MissingArgumentException e) {
                    println("Invalid Command: $e.message")
                    HelpFormatter help = new HelpFormatter()
                    help.printHelp("vm-utils " + action, factory)
                    System.exit(EXIT_INVALID_ARGS)
                }

                if (cmd.hasOption("help")) {
                    HelpFormatter help = new HelpFormatter()
                    help.printHelp("vm-utils " + action, factory)

                    System.exit(EXIT_OK)
                }

                try {
                    executableAction.execute(cmd, new ConsoleOutputWriter())
                } catch (InvalidOptionsException e) {
                    println("Invalid Command: $e.message")
                    HelpFormatter help = new HelpFormatter()
                    help.printHelp("vm-utils " + action, factory)
                    System.exit(EXIT_INVALID_ARGS)
                }

            }

        }

    }

    private def printHelp() {
        println("The $appName CLI application has the following form:")
        println()
        println("$appName (actions) (args)")
        println("you can also see more details on an action by:")
        println("$appName <action> -h")
        println()
        println("-----Actions-----")
        println()
        for (String action: actionsMap.keySet()) {
            println("$action: " + actionsMap.get(action).getDesription())
        }
        println()
        println("-----Basic Options------")
        println("--help [prints this help message]")


    }

    private void configureLogging() {
    }

    //for testing.
    public static void main (String ... args) {
        Main app = new Main("sample-app", ImmutableMap.<String,ApplicationAction>builder().build());
        app.executeApplication(args);
    }

}
