package org.example.trafficlawhelper.dto.response;

import java.util.List;

public record QuizResponse(String question, List<String> options, String answer) {
}
