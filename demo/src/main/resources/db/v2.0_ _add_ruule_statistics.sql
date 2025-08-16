CREATE TABLE rule_statistics
(id UUID PRIMARY KEY,
rule_id UUID NOT NULL REFERENCES
dynamic_rule(id)ON DELETE CASCADE,
count BIGINT NOT NULL DEFAULT 0);
CREATE INDEX idx_rule_statistics_rule_id
ON rule_statistics(rule_id);