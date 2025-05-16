/*
package utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/convert")
public class gherkinToFeatureController {

    @Autowired
    private gherkinToFeatureService gherkinToFeatureService;

    @PostMapping
    public ResponseEntity<String> convertGherkinToFeature(@RequestBody String gherkinStory) {
        try {
            String featureFileContent = gherkinToFeatureService.convertGherkinToFeature(gherkinStory);
            return ResponseEntity.ok(featureFileContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}*/
