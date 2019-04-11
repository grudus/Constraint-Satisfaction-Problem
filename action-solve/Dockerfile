FROM zenika/kotlin:1.3.21-alpine

LABEL "com.github.actions.name"="Solve games"
LABEL "com.github.actions.description"="Solve futoshiki and sudoku games using csp algorithms"
LABEL "com.github.actions.icon"="mic"
LABEL "com.github.actions.color"="purple"

LABEL "repository"="http://github.com/grudus/Constraint-Satisfaction-Problem"
LABEL "homepage"="http://github.com/actions"
LABEL "maintainer"="grudus"

ADD entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
